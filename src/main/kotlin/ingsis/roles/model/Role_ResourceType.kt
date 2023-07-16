package ingsis.roles.model

import java.util.*
import javax.persistence.*

@Entity
class Role_ResourceType {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null

    @ManyToOne
    @JoinColumn(name = "resourceTypeId", nullable = false)
    var resourceType: ResourceType? = null

    @ManyToOne
    @JoinColumn(name = "roleId", nullable = false)
    var role: Role? = null
}