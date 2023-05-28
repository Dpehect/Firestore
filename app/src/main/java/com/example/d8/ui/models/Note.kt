package com.example.d8.ui.models

class Note {
    var title: String? = null
    var content: String? = null
    var timestamp: Long = 0

    constructor() {
        // No-arg (parametresiz) yapıcı
    }

    constructor(title: String?, content: String?, timestamp: Long) {
        this.title = title
        this.content = content
        this.timestamp = timestamp
    }
}
