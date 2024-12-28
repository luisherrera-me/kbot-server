package com.kuby.kbot.controllers

import com.kuby.kbot.entities.robot.Robot
import com.kuby.kbot.entities.robot.RobotReques
import com.kuby.kbot.entities.sensorData.SensorData
import com.kuby.kbot.entities.sensorData.SensorDataDTO
import com.kuby.kbot.services.RobotService
import com.kuby.kbot.services.SensorDataService
import com.kuby.kbot.services.SensorService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/dataSensor")
class DataSensorController(
    private val sensorService: SensorService,
    private val robotService: RobotService,
    private val sensorDataService: SensorDataService
) {
    @PostMapping
    fun saveDataSensor(@RequestBody sensorDataRequest: SensorDataDTO): ResponseEntity<Any> {
        return try {

            val sensorRequest = sensorService.findById(sensorDataRequest.sensorId)
                ?: return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf(
                    "status" to HttpStatus.BAD_REQUEST.value(),
                    "message" to "Sensor no encontrado con id: ${sensorDataRequest.sensorId}",
                    "errorCode" to "USER_NOT_FOUND"
                ))

            val RobotRequest = robotService.findById(sensorDataRequest.robotId)
                ?: return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf(
                    "status" to HttpStatus.BAD_REQUEST.value(),
                    "message" to "Robot no encontrado con id: ${sensorDataRequest.robotId}",
                    "errorCode" to "USER_NOT_FOUND"
                ))

            val DataSensor = SensorData(
                sensor = sensorRequest,
                robot = RobotRequest,
                value = sensorDataRequest.value,
                latitude = sensorDataRequest.latitude,
                longitude = sensorDataRequest.longitude
            )
            val savedUser = sensorDataService.save(DataSensor, sensorDataRequest)

            ResponseEntity.status(HttpStatus.CREATED).body(mapOf(
                "status" to HttpStatus.CREATED.value(),
                "message" to "Usuario creado exitosamente.",
                "data" to savedUser
            ))
        } catch (e: Exception) {
            handleError(e, "Error al guarda la data")
        }
    }

    @GetMapping
    fun getAllSensorData(): ResponseEntity<List<SensorData>> {
        val sensorDataList = sensorDataService.findAll()
        return ResponseEntity.ok(sensorDataList)
    }

    @GetMapping("/{id}")
    fun getDataSensorByIdSensor(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            val dataSensor = sensorDataService.findByIdSensor(id)
            ResponseEntity.ok(mapOf(
                "status" to HttpStatus.OK.value(), // Código de estado HTTP
                "message" to "Datos del sensor encontrados.",
                "data" to dataSensor
            ))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                mapOf(
                    "status" to HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "message" to "Error al obtener los datos del sensor con id: $id",
                    "error" to e.message
                )
            )
        }
    }

    private fun handleError(e: Exception, customMessage: String): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mapOf(
            "status" to HttpStatus.INTERNAL_SERVER_ERROR.value(), // Código de estado HTTP
            "message" to customMessage,
            "errorCode" to "INTERNAL_SERVER_ERROR",
            "details" to e.localizedMessage
        ))
    }

}