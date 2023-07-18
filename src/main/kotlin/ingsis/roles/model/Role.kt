package ingsis.roles.model

import java.util.UUID
import javax.persistence.*

@Entity
class Role {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null

    @ManyToOne
    @JoinColumn(name = "resourceTypeId", nullable = false)
    var resourceType: ResourceType? = null

    @Column(name = "name", nullable = false)
    var name: String? = null
}