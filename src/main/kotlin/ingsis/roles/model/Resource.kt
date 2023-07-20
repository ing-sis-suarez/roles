package ingsis.roles.model

import java.util.*
import javax.persistence.*

@Entity
@Table(
    name = "resource",
    uniqueConstraints = [UniqueConstraint(columnNames = ["resource_type_id", "resource_id"])]
)


class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    var id: UUID? = null

    @ManyToOne
    @JoinColumn(name = "resource_type_id", nullable = false)
    var resourceType: ResourceType? = null


    @Column(name = "resource_id", nullable = false)
    var resourceId: UUID? = null


    constructor(resourceType: ResourceType, resourceId: UUID) {
        this.resourceId = resourceId
        this.resourceType = resourceType
    }
}
