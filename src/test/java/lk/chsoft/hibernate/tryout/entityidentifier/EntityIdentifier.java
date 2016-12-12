package lk.chsoft.hibernate.tryout.entityidentifier;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class EntityIdentifier implements Serializable {

    @Column(name = "entity_id", nullable = true)
    private Long entityId;
    @Column(name = "entity_class", nullable = true)
    private Class entityClass;

    public EntityIdentifier() {
    }

    public EntityIdentifier(Class entityClass, Long entityId) {
        this.entityClass = entityClass;
        this.entityId = entityId;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public Class getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class entityClass) {
        this.entityClass = entityClass;
    }
}
