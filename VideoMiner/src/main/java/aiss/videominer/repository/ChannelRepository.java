package aiss.videominer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aiss.videominer.model.Channel;

public interface ChannelRepository extends JpaRepository<Channel, String> {
}
