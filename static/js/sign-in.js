var categoryFilterApi = ({username}) => `/api/customers/${username}/`
/* global Vue, axios */

const app = Vue.createApp({

    data() {
        return {
            // models map (comma separated key/value pairs)
            username: new String(),
        };
    },

    mounted() {
        // semicolon separated statements
    },

    methods: {
        // comma separated function declarations
        getCustomer(username) {
            axios.get(categoryFilterApi({'username': username}))
                .then( response => {
                    dataStore.commit('setCustomer', response.data);
                    window.location = 'view-products.html';
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

import { dataStore } from './data-store.js';
app.use(dataStore);	

// register the navigation menu under the <navmenu> tag
app.component('navmenu', navigationMenu);

// mount the page - this needs to be the last line in the file
app.mount("main");