package com.oneearth.shetkari.data


import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "result")
data class ApiResponse(
    @field:XmlElement(name = "records")
    var records: List<Record> = mutableListOf()
)