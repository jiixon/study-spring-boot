package com.gachon.board.repository;

import com.gachon.board.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity,Long> {
    //Jpa검색할때 다중조회 단건조회 따라서 리턴타입 두가지
    //단건조회 떄 , Optional<Entity>
    //다중조회 때 , List<Entity>
    Optional<MemberEntity> findByEmail(String email);
}
