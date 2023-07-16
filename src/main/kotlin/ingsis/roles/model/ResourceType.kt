package ingsis.roles.model

import java.util.*
import javax.persistence.*

@Entity
class ResourceType {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null

    @Column(name = "name", nullable = false, unique = true)
    var name: String? = null
}