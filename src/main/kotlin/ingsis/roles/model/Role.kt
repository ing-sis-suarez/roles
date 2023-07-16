package ingsis.roles.model

import javax.persistence.*

@Entity
class Role {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @ManyToOne
    @JoinColumn(name = "resourceTypeId", nullable = false)
    var resourceType: ResourceType? = null

    @Column(name = "name", nullable = false)
    var name: String? = null
}