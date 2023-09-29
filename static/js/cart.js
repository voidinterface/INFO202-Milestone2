"use strict";

var saleApi = "/api/sales/";

class SaleItem {
	constructor(product, quantityPurchased) {
		this.product = product;
		this.quantityPurchased = quantityPurchased;
		this.salePrice = product.listPrice;
	}
}

class Sale {
	constructor(customer, items) {
		this.customer = customer;
		this.items = items;
	}
}

/* global Vue, axios */

const app = Vue.createApp({

    data() {
        return {
            // models map (comma separated key/value pairs)
            quantity: new Number(),
            subtotal: new Number(),
        };
    },
    
    computed: Vuex.mapState({
        product: 'selectedProduct',
        items: 'items',
        customer: 'customer'
    }),

    mounted() {
        // semicolon separated statements
        this.updateTotal();
    },

    methods: {
        // comma separated function declarations
        addItem() {
            if (this.quantity > 0) {
                dataStore.commit("addItem", new SaleItem(this.product, this.quantity));
            }
            window.location = "view-products.html"
        },

        checkOut() {
            let sale = new Sale(this.customer, this.items);
            axios.post(saleApi, sale)
                .then( () => {
                    dataStore.commit("clearItems");
                    window.location = "confirmation.html";
                }).catch(error => {
                    console.error(error);
                    alert('An error occured, check the console for details!');
                });
        },

        updateTotal() {
            this.subtotal = this.items.reduce(
                (sum, item) => sum + (item.salePrice * item.quantityPurchased), 0);
        },

        cartEmpty() {
            return this.items.length === 0;
        }
    },

    // other modules
    mixins: [NumberFormatter]

});

// other component imports go here
// import data store
import { dataStore } from './data-store.js'
app.use(dataStore);
// import the navigation menu
import { navigationMenu } from './navigation-menu.js';

import { NumberFormatter } from './number-formatter.js';

// register the navigation menu under the <navmenu> tag
app.component('navmenu', navigationMenu);

// mount the page - this needs to be the last line in the file
app.mount("main");