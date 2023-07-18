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
    var userId: String? = null

    @ManyToOne
    @JoinColumn(name = "roleId", nullable = false)
    var role: Role? = null

    @ManyToOne
    @JoinColumn(name = "resourceId", nullable = false)
    var resource: Resource? = null

    constructor(userId: String?, roleId: Role?, resourceId: Resource?) {
        this.userId = userId
        this.role = roleId
        this.resource = resourceId
    }
}