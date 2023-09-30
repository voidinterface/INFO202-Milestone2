package domain;

import java.awt.image.DataBufferByte;
import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Objects;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNegative;
import net.sf.oval.constraint.NotNull;

/**
 * @author Mark George
 */
public class Product {

	@NotNull(message = "ID must be provided.")
	@NotBlank(message = "ID must be provided.")
	@Length(min = 2, message = "ID must contain at least two characters.")
	private String productId;

	@NotNull(message = "Name must be provided.")
	@NotBlank(message = "Name must be provided.")
	@Length(min = 2, message = "Name must contain at least two characters.")
	private String name;

	private String description;

	@NotNull(message = "Category must be provided.")
	@NotBlank(message = "Category must be provided.")
	@Length(min = 2, message = "Category must contain at least two characters.")
	private String category;

	@NotNull(message = "Price must be provided.")
	@NotNegative(message = "Price must be zero or greater.")
	private BigDecimal listPrice;

	@NotNull(message = "Quantity must be provided.")
	@NotNegative(message = "Quantity must be zero or greater.")
	private BigDecimal quantityInStock;
        
        private transient Blob image;
        
        private BigDecimal quantitySold;

	public Product() {
	}

	public Product(String productId, String name, String description, String category, BigDecimal listPrice, BigDecimal quantityInStock) {
		this.productId = productId;
		this.name = name;
		this.description = description;
		this.category = category;
		this.listPrice = listPrice;
		this.quantityInStock = quantityInStock;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public BigDecimal getListPrice() {
		return listPrice;
	}

	public void setListPrice(BigDecimal listPrice) {
		this.listPrice = listPrice;
	}

	public BigDecimal getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(BigDecimal quantityInStock) {
		this.quantityInStock = quantityInStock;
	}
        
        public void setImage(Blob imageBuffer) {
            this.image = imageBuffer;
        }
        
        public Blob getImage() {
            return this.image;
        }

        public BigDecimal getQuantitySold() {
            return quantitySold;
        }

        public void setQuantitySold(BigDecimal quantitySold) {
            this.quantitySold = quantitySold;
        }
        

	@Override
	public String toString() {
		return "ID: " + productId + ", Name: " + name;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 89 * hash + Objects.hashCode(this.productId);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Product other = (Product) obj;
		if (!Objects.equals(this.productId, other.productId)) {
			return false;
		}
		return true;
	}

}
