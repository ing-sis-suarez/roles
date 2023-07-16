package ingsis.roles.model

import java.util.*
import javax.persistence.*

@Entity
class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private var id: UUID? = null

    @ManyToOne
    @JoinColumn(name = "resourceTypeId", nullable = false)
    private var resourceType: ResourceType? = null

    @Column(name = "resourceId", nullable = false)
    private var resourceId: UUID? = null


    constructor(resourceType: ResourceType, resourceId: UUID) {
        this.resourceId = resourceId
        this.resourceType = resourceType
    }
}
