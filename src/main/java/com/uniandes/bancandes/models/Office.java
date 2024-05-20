package com.uniandes.bancandes.models;

import java.util.List;

import org.bson.types.ObjectId;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Office {
   
    @Id
    private ObjectId id;
    private String name;
    private String address;
    private List<PointService> point_services;
}
