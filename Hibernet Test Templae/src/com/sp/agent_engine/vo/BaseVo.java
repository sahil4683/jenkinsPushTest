package com.sp.agent_engine.vo;

import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.TIMESTAMP;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Thread
 */
@Getter
@Setter
@ToString
@MappedSuperclass
public class BaseVo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Temporal(TIMESTAMP)
    protected Date insertDate;
    @Temporal(TIMESTAMP)
    protected Date updateDate;

    public BaseVo() {
    }

    public BaseVo(Date insertDate, Date updateDate) {
        this.insertDate = insertDate;
        this.updateDate = updateDate;
    }
}
