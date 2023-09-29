export const dataStore = Vuex.createStore({

    state () {
        verification: null;
        customer: null;
        items: null;
        selectedProduct: null;
    },

    mutations: {
        setCustomer(state, customer) {
            state.customer = customer;
            state.items = new Array();
        },
        selectProduct(state, product) {
            state.selectedProduct = product;
        },
        addItem(state, item) {
            state.items.push(item);
        },
        clearItems(state) {
            state.items = new Array();
        },
    },

    plugins: [window.createPersistedState({storage: window.sessionStorage})]

});