package cepn.defaut.models.common;

import org.springframework.data.jpa.domain.AbstractPersistable;

import java.io.Serializable;

public abstract class AbstractPersistableWithIdSetter<PK extends Serializable> extends AbstractPersistable<PK> {

    public void setId(PK id){
        super.setId(id);
    }
}
