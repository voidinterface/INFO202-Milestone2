"use strict";

export const navigationMenu = {
    computed: {
        signedIn() {
            return this.customer != null;
        },
        ...Vuex.mapState({
            customer: "customer"
        })
    },
    
    template:
            `
            <nav>
                <a href="/">Home</a>
                <div v-if="!signedIn">
                    <a href="sign-in.html">Sign In</a>
                </div>
                <div v-else>
                    <a href="view-products.html">Browse Products</a>
                    <a >View Cart</a>
                    <a href="#" @click="signOut()">Sign Out</a>
                    <p>Welcome {{customer.firstName}}!</p>
                </div>
            </nav>
            `,
    
    methods: {
        signOut() {
            sessionStorage.clear();
            window.location = '.';
        }
    }
};


