package com.guotion.material.service.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by wx on 15/11/22.
 */
@Entity(name = "t_prov_city_area")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Address {
    int id;
    String areaname;
    int parentno;
    String areacode;
    byte arealevel;
    String typename;



    int leaf;

    Set<Premises>premises;

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "areaname")
    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    @Column(name = "parentno")
    public int getParentno() {
        return parentno;
    }

    public void setParentno(int parentno) {
        this.parentno = parentno;
    }

    @Column(name = "areacode")
    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    @Column(name = "arealevel")
    public byte getArealevel() {
        return arealevel;
    }

    public void setArealevel(byte arealevel) {
        this.arealevel = arealevel;
    }

    @Column(name = "typename")
    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }



    @OneToMany(targetEntity = Premises.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    public Set<Premises> getPremises() {
        return premises;
    }

    public void setPremises(Set<Premises> premises) {
        this.premises = premises;
    }

    @Id
    @Column(name = "areano")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    public int getId() {
        return id;
    }

    public Address() {
        premises=new HashSet<>();
    }
    public int getLeaf() {
        return leaf;
    }

    public void setLeaf(int leaf) {
        this.leaf = leaf;
    }
}
