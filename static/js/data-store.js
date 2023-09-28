export const dataStore = Vuex.createStore({

    state () {
        verification: null;
        customer: null;
    },

    mutations: {
        setCustomer(state, customer) {
                state.customer = customer;
        },
    },

    plugins: [window.createPersistedState({storage: window.sessionStorage})]

});