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
            <header>
                <h1 class="header-title">Widgets and Doohickies</h1>
                <nav>
                    <ul>
                        <li><a href="/">Home</a></li>
                        <li v-if="signedIn"><a href="view-products.html">Products</a></li>
                    </ul>
                </nav>
                <div class="user-section" v-if="!signedIn">
                    <a href="sign-in.html"><button>Sign In</button></a>
                    <a href="create-account.html"><button>Sign Up</button></a>
                </div>
                <div class="user-section" v-else>
                    <p>Welcome {{customer.firstName}}!</p>
                    <a><button>View Cart</button></a>
                    <a href="#" @click="signOut()"><button>Sign Out</button></a>
                </div>
            </header>
            `,
    
    methods: {
        signOut() {
            sessionStorage.clear();
            window.location = '.';
        }
    }
};