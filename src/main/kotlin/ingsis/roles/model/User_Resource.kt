package ingsis.roles.model

import javax.persistence.*

@Entity
class User_Resource {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(name = "userId", nullable = false)
    var userId: Long? = null

    @ManyToOne
    @JoinColumn(name = "roleId", nullable = false)
    var roleId: Role? = null

    @ManyToOne
    @JoinColumn(name = "resourceId", nullable = false)
    var resourceId: Resource? = null
}