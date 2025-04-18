package org.example.advancedrealestate_be.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "auction_details")
@Getter
@Setter
public class AuctionDetail {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @Column(columnDefinition = "text")
    private String note;
    private String result;
    private double bidAmount;
    private String status;
    private String identity_key;

    @ManyToOne
    @JoinColumn(name = "auction_id", nullable = true)
    //annotion này giúp gỡ lỗi lặp vô hạn khi mapper
    @JsonBackReference("auction_detail-auctions")
    private Auction auction;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = true)
    @JsonBackReference("auction_detail-users")
    private User client;

    @CreationTimestamp
    private LocalDateTime createdOn;
    @UpdateTimestamp
    private LocalDateTime updatedOn;
}
