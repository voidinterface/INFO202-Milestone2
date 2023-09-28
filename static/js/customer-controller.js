var signInApi = ({username}) => `/api/customers/${username}/`;
var signUpApi = `/api/register/`;
/* global Vue, axios */

const app = Vue.createApp({

    data() {
        return {
            // models map (comma separated key/value pairs)
            customer: new Object()
        };
    },

    mounted() {
        // semicolon separated statements
    },

    methods: {
        // comma separated function declarations
        signIn(username) {
            axios.get(signInApi({'username': this.customer.username}))
                .then( response => {
                    this.customer = response.data;
                    this.storeCustomer();
                }).catch(error => {
                    console.error(error);
                    alert('An error occured, check the console for details!');
                });
            
        },
        signUp() {
            axios.post(signUpApi, this.customer)
                .then( response => {
                    this.storeCustomer();
                }).catch(error => {
                    console.error(error);
                    alert('An error occured, check the console for details!');
                });
            
        },
        storeCustomer() {
            dataStore.commit('setCustomer', this.customer);
            window.location = 'view-products.html';
        }
    },

    // other modules
    mixins: []

});

// other component imports go here
// import the navigation menu
import { navigationMenu } from './navigation-menu.js';

import { dataStore } from './data-store.js';
app.use(dataStore);	

// register the navigation menu under the <navmenu> tag
app.component('navmenu', navigationMenu);

// mount the page - this needs to be the last line in the file
app.mount("main");