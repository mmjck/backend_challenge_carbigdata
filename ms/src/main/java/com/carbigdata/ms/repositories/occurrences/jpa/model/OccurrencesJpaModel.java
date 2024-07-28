package com.carbigdata.ms.repositories.occurrences.jpa.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.carbigdata.ms.domain.occurrences.entities.OccurrencesStatus;
import com.carbigdata.ms.repositories.address.jpa.model.AddressJpaModel;
import com.carbigdata.ms.repositories.client.jpa.model.ClientJpaModel;
import com.carbigdata.ms.repositories.occurrences.jpa.mapper.OccurrencesListDTO;
import com.carbigdata.ms.repositories.occurrences_image.jpa.model.OccurrencesImageJpaModel;

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

import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;

import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;

@Entity
@Table(name = "tb_occurrences")
@Getter
@Setter
@SqlResultSetMapping(name = "OccurrencesJpaModel.OccurrencesListDTO",

                classes = {
                                @ConstructorResult(targetClass = OccurrencesListDTO.class, columns = {
                                                @ColumnResult(name = "id", type = String.class),
                                                @ColumnResult(name = "status", type = String.class),
                                                @ColumnResult(name = "createdAt", type = String.class),
                                                @ColumnResult(name = "images", type = String.class),
                                                @ColumnResult(name = "state", type = String.class),
                                                @ColumnResult(name = "city", type = String.class),
                                                @ColumnResult(name = "zipCode", type = String.class),
                                                @ColumnResult(name = "district", type = String.class),
                                                @ColumnResult(name = "fullName", type = String.class),
                                                @ColumnResult(name = "cpf", type = String.class),
                                                @ColumnResult(name = "clientId", type = Integer.class),
                                }) })
@NamedNativeQuery(query = "select " +
                "o.id as id, " +
                "o.status as status, " +
                "o.created_at as createdAt, " +
                "json_agg( " +
                "	jsonb_strip_nulls(  " +
                "        jsonb_build_object( " +
                "            'hash', CASE WHEN i.hash IS NOT NULL THEN i.hash ELSE NULL end, " +
                "            'path', CASE WHEN i.path IS NOT NULL THEN i.path ELSE NULL end " +
                "        ) ) ) as images, " +
                "a.state as state ," +
                "a.city as city ," +
                "a.zip_code as zipCode,   " +
                "a.district as district, " +
                "c.full_name as fullName, " +
                "c.cpf as cpf ," +
                "c.id as clientId " +
                "from tb_occurrences o " +
                "left join tb_occurrences_images i on o.id = i.occurrence_id " +
                "left join tb_address a on o.address_id = a.id " +
                "left join tb_clients c on o.client_id = c.id " +
                "where " +
                "	(:full_name is null or c.full_name ilike :full_name) " +
                "	OR (:cpf is null  or c.cpf = :cpf) " +
                "	OR (:city is null or a.city ilike :city) " +
                "   group by " +
                "	c.full_name, " +
                "	c.cpf, " +
                "	c.id, " +
                "	o.id, " +
                "	o.created_at, " +
                "	a.state, " +
                "	a.city, " +
                "	a.zip_code, " +
                "	a.district " ,
                resultSetMapping = "OccurrencesJpaModel.OccurrencesListDTO", name = "OccurrencesJpaModel.findAll")
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
        private List<OccurrencesImageJpaModel> imagesOccurences;

}
