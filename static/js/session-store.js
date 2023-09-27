export const sessionStore = Vuex.createStore({

    state () {
        verification: null;
        customer: null;
    },

    mutations: {
        setCustomer(state, customer) {
                state.scustomer = customer;
        },
    },

    plugins: [window.createPersistedState({storage: window.sessionStorage})]

});