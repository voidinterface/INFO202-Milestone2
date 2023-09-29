"use strict";

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
        this.subtotal = this.items.reduce(
            (sum, item) => sum + (item.salePrice * item.quantityPurchased), 0);
    },

    methods: {
        // comma separated function declarations
        addItem() {
            if (this.quantity > 0) {
                dataStore.commit("addItem", new SaleItem(this.product, this.quantity));
            }
            window.location = "view-products.html"
        }
    },

    // other modules
    mixins: []

});

// other component imports go here
// import data store
import { dataStore } from './data-store.js'
app.use(dataStore);
// import the navigation menu
import { navigationMenu } from './navigation-menu.js';

// register the navigation menu under the <navmenu> tag
app.component('navmenu', navigationMenu);

// mount the page - this needs to be the last line in the file
app.mount("main");