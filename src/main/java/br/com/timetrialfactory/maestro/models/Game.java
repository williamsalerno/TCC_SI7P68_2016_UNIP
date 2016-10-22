package br.com.timetrialfactory.maestro.models;

import static br.com.timetrialfactory.maestro.utils.FormatterStringUtil.formatToUTF8;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "games", catalog = "names")
public class Game {

	private static final String DOWNLOAD_DISRUPTION = "https://s3.amazonaws.com/timetrialgames/Disruption/Disruption.rar";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;

	@NotBlank
	@Length(min = 5)
	@Column(name = "title", nullable = false, unique = true)
	private String title;

	@NotNull
	@Min(value = 0)
	@Digits(integer = 3, fraction = 2)
	@Column(name = "price", nullable = false)
	private BigDecimal price;

	@NotBlank
	@Column(name = "description", nullable = false)
	private String description;

	@NotNull
	@Column(name = "downloadLink", nullable = false)
	private String downloadLink = DOWNLOAD_DISRUPTION;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = formatToUTF8(title);
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = formatToUTF8(description);
	}

	public String getDownloadLink() {
		return downloadLink;
	}

	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}

}
