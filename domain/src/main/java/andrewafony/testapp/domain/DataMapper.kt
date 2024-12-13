package andrewafony.testapp.domain

interface DataMapper<T> {

    fun toDomain(): T
}

interface DatabaseMapper<T> {

    fun toEntity() : T
}