/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sp.agent_engine.vo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author sahil
 */
@Entity
@Table(name = "UserInfo")
@Getter
@Setter
@ToString
public class User extends BaseVo implements Serializable{
    private String name;
    private String dept;
}
