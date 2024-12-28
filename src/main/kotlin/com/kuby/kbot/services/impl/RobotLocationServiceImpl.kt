package com.kuby.kbot.services.impl

import com.kuby.kbot.entities.robot.RobotLocation
import com.kuby.kbot.repository.robot.RobotLocationRepository
import com.kuby.kbot.services.RobotLocationService
import org.springframework.stereotype.Service
import java.util.*

@Service
class RobotLocationServiceImpl(private val robotLocationRepository: RobotLocationRepository) : RobotLocationService {

    override fun findAll(): List<RobotLocation> {
        return robotLocationRepository.findAll()
    }

    override fun findById(id: Long): Optional<RobotLocation> {
        return robotLocationRepository.findById(id)
    }

    override fun save(location: RobotLocation): RobotLocation {
        return robotLocationRepository.save(location)
    }

    override fun update(id: Long, location: RobotLocation): RobotLocation {
        TODO("Not yet implemented")
    }

    override fun delete(id: Long) {
        if (robotLocationRepository.existsById(id)) {
            robotLocationRepository.deleteById(id)
        } else {
            throw Exception("Location not found")
        }
    }
}