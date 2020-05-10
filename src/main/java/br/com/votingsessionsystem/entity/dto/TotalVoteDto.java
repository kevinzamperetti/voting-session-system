package br.com.votingsessionsystem.entity.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TotalVoteDto {

    private Integer no;

    private Integer yes;
}
