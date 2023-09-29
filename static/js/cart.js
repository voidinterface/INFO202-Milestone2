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
        };
    },
    
    computed: Vuex.mapState({
        product: 'selectedProduct',
        items: 'items',
        customer: 'customer'
    }),

    mounted() {
        // semicolon separated statements
    },

    methods: {
        // comma separated function declarations
        addItem() {
            dataStore.commit("addItem", new SaleItem(this.product, this.quantity));
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