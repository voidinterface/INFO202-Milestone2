export const navigationMenu = {
	template:
		`
		<nav>
                    <a href="index.jsp">Home</a>
                    <div v-if="true">
                        <a href="sign-in.html">Sign In</a>
                    </div>
                    <div v-else>
                        <a href="view-products.html">Browse Products</a>
                        <a >View Cart</a>
                        <a href="sign-out">Sign Out</a>
                        <p>Welcome</p>
                    </div>
                </nav>
		`
};