package com.oneearth.shetkari

class HelperClass() {
    var name: String? = null
        get() = field
        set(value) {
            field = value
        }

    var email: String? = null
        get() = field
        set(value) {
            field = value
        }

    var username: String? = null
        get() = field
        set(value) {
            field = value
        }

    var password: String? = null
        get() = field
        set(value) {
            field = value
        }

    constructor(name: String?, email: String?, username: String?, password: String?) : this() {
        this.name = name
        this.email = email
        this.username = username
        this.password = password
    }
}
