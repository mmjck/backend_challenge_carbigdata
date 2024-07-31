package com.carbigdata.ms.repositories.occurrences.jpa.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.carbigdata.ms.core.domain.occurrences.OccurrencesStatus;
import com.carbigdata.ms.repositories.address.jpa.model.AddressJpaModel;
import com.carbigdata.ms.repositories.client.jpa.model.ClientJpaModel;
import com.carbigdata.ms.repositories.images.jpa.model.ImagesJpaModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_occurrences")
@Getter
@Setter
public class OccurrencesJpaModel {
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(name = "status", nullable = false)
        @Enumerated(EnumType.STRING)
        private OccurrencesStatus status;

        @Column(name = "created_at")
        @CreationTimestamp
        private LocalDateTime createdAt;

        @Column(name = "client_id")
        private Integer clientId;

        @Column(name = "address_id")
        private Integer addressId;

        public OccurrencesJpaModel() {
        }

        public OccurrencesJpaModel(Integer id, Integer clientId, Integer addressId, OccurrencesStatus status,
                        LocalDateTime createdAt) {
                this.id = id;
                this.status = status;
                this.createdAt = createdAt;
                this.clientId = clientId;
                this.addressId = addressId;
        }

        @ManyToOne
        @JoinColumn(name = "client_id", insertable = false, updatable = false)
        private ClientJpaModel client;

        @ManyToOne
        @JoinColumn(name = "address_id", insertable = false, updatable = false)
        private AddressJpaModel address;

        @OneToMany(mappedBy = "id", cascade = CascadeType.REMOVE)
        private List<ImagesJpaModel> imagesOccurences;

}
