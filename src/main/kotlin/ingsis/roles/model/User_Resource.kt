package ingsis.roles.model

import java.util.UUID
import javax.persistence.*

@Entity
class User_Resource {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null

    @Column(name = "userId", nullable = false)
    var userId: UUID? = null

    @ManyToOne
    @JoinColumn(name = "roleId", nullable = false)
    var roleId: Role? = null

    @ManyToOne
    @JoinColumn(name = "resourceId", nullable = false)
    var resourceId: Resource? = null

    constructor(userId: UUID?, roleId: Role?, resourceId: Resource?) {
        this.userId = userId
        this.roleId = roleId
        this.resourceId = resourceId
    }
}