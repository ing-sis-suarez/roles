package ingsis.roles.model

import java.util.*
import javax.persistence.*

@Entity
class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    var id: UUID? = null

    @ManyToOne
    @JoinColumn(name = "resourceTypeId", nullable = false)
    var resourceType: ResourceType? = null

    @Column(name = "resourceId", nullable = false)
    var resourceId: UUID? = null


    constructor(resourceType: ResourceType, resourceId: UUID) {
        this.resourceId = resourceId
        this.resourceType = resourceType
    }
}
