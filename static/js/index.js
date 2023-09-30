var productsApi = '/api/products/'

// create the Vue controller
const app = Vue.createApp({
    data() {
        return {
            // models map (comma separated key/value pairs)
            products: new Array(),
        };
    },

    mounted() {
        // semicolon separated statements
        this.getFeaturedProducts();
    },

    methods: {
        // comma separated function declarations
        getFeaturedProducts() {
            axios.get(productsApi)
                .then( response => {
                    this.products = response.data;
                    this.products.sort((a, b) => b.quantitySold - a.quantitySold);
                    this.products = this.products.slice(0, 3);
                }).catch(error => {
                    console.error(error);
                    alert('An error occured, check the console for details!');
                });
        },
        getImage(product) {
            return `/api/products/${product.productId}/image/`;
        }
    },

    // other modules
    mixins: [NumberFormatter]
});

// import data store
import { dataStore } from './data-store.js'
app.use(dataStore);

// import the navigation menu
import { navigationMenu } from './navigation-menu.js';

import { NumberFormatter } from './number-formatter.js';

// register the navigation menu under the <navmenu> tag
app.component('navmenu', navigationMenu);

// attach the controller to the <main> tag
app.mount("main");