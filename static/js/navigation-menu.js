export const navigationMenu = {
	template:
		`
		<nav>
                    <a href="index.jsp">Home</a>
                    <div v-if="true">
                        <a href="sign-in.jsp">Sign In</a>
                    </div>
                    <div v-else>
                        <a href="view-products.jsp">Browse Products</a>
                        <a >View Cart</a>
                        <a href="sign-out">Sign Out</a>
                        <p>Welcome</p>
                    </div>
                </nav>
		`
};