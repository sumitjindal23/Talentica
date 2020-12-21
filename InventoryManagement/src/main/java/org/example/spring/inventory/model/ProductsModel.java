package org.example.spring.inventory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.example.spring.inventory.entity.enums.EmployeeTypeEnum;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductsModel {

    @JsonProperty(value = "productId")
    private String productId;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "categoryName")
    private String categoryName;

    @JsonProperty(value = "userName")
    private String userName;

    @Column(name = "lastUpdatedTime")
    private Timestamp lastUpdatedDateTime;

    @Column(name = "deleted")
    private Boolean deleted;

}
