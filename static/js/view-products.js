var productsApi = '/api/products/'
var categoriesApi = '/api/categories/'
var categoryFilterApi = ({category}) => `/api/categories/${category}/`
/* global Vue, axios */

const app = Vue.createApp({

    data() {
        return {
            // models map (comma separated key/value pairs)
            products: new Array(),
            categories: new Array()
        };
    },

    mounted() {
        // semicolon separated statements
        this.getProducts();
        this.getCategories();
    },

    methods: {
        // comma separated function declarations
        getProducts() {
            axios.get(productsApi)
                .then( response => {
                    this.products = response.data;
                }).catch(error => {
                    console.error(error);
                    alert('An error occured, check the console for details!');
                });
        },
        getCategories() {
            axios.get(categoriesApi)
                .then(response => {
                    this.categories = response.data;
                }).catch (error => {
                    console.error(error);
                    alert('An error occured, check the console for details!');
                });
        },
        filterByCategory(category) {
            axios.get(categoryFilterApi({'category': category}))
                .then( response => {
                    this.products = response.data;
                }).catch(error => {
                    console.error(error);
                    alert('An error occured, check the console for details!');
                });
        },
    },

    // other modules
    mixins: []

});

// other component imports go here
// import the navigation menu
import { navigationMenu } from './navigation-menu.js';

// register the navigation menu under the <navmenu> tag
app.component('navmenu', navigationMenu);

// mount the page - this needs to be the last line in the file
app.mount("main");