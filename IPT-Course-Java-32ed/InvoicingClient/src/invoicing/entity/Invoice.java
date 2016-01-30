package invoicing.entity;

import java.io.Serializable;
import javax.persistence.*;

import invoicing.entity.Position;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the invoice database table.
 * 
 */
public class Invoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private long number;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date date;

	//bi-directional many-to-one association to Position
	@OneToMany(mappedBy="invoice")
	private List<Position> positions;

	//bi-directional many-to-one association to Contragent
	@ManyToOne
	@JoinColumn(name="receiver", nullable=false)
	private Contragent receiver;

	//bi-directional many-to-one association to Contragent
	@ManyToOne
	@JoinColumn(name="issuer", nullable=false)
	private Contragent issuer;

	public Invoice() {
	}

	public Invoice(long number, Contragent issuer, Contragent receiver) {
		this.number = number;
		this.issuer = issuer;
		this.receiver = receiver;
		this.positions = new ArrayList<>();
	}
	
	public Invoice(long number, Date date, Contragent issuer, Contragent receiver, List<Position> positions) {
		this.number = number;
		this.date = date;
		this.issuer = issuer;
		this.receiver = receiver;
		this.positions = positions;
	}
	
	public long getNumber() {
		return this.number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Position> getPositions() {
		return this.positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	public Position addPosition(Position position) {
		getPositions().add(position);
		position.setInvoice(this);

		return position;
	}

	public Position removePosition(Position position) {
		getPositions().remove(position);
		position.setInvoice(null);

		return position;
	}

	public Contragent getReceiver() {
		return this.receiver;
	}

	public void setReceiver(Contragent receiver) {
		this.receiver = receiver;
	}

	public Contragent getIssuer() {
		return this.issuer;
	}

	public void setIssuer(Contragent issuer) {
		this.issuer = issuer;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Invoice [number=").append(number).append(", date=").append(date).append(", positions=")
				.append(positions).append(", receiver=").append(receiver).append(", issuer=").append(issuer)
				.append("]");
		return builder.toString();
	}

}