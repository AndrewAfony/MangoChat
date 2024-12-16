package andrewafony.testapp.data.cache

class UserCache(
    private var name: String = ""
) {

    fun userName() = name

    fun updateName(name: String) {
        this.name = name
    }
}