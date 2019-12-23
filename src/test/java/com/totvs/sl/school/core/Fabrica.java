package com.totvs.sl.school.core;

import java.util.ArrayList;
import java.util.List;

import com.totvs.sl.school.core.aluno.domain.model.AlunoId;
import com.totvs.sl.school.core.disciplina.domain.model.DisciplinaId;
import com.totvs.sl.school.core.professor.domain.model.ProfessorId;

public class Fabrica {

    public static final ProfessorId professorId1 = ProfessorId.generate();
    public static final ProfessorId professorId2 = ProfessorId.generate();
    public static final ProfessorId professorId3 = ProfessorId.generate();

    public static final String disciplinaDescricao1 = "Português";
    public static final String disciplinaSigla1 = "POR";
    public static final int disciplinaCargaHoraria1 = 3;

    public static final String disciplinaDescricao2 = "Matemática";
    public static final String disciplinaSigla2 = "MAT";
    public static final int disciplinaCargaHoraria2 = 4;

    
    public static final String turmaDescricao1 = "Turma I";
    public static final int turmaAnoLetivo = 2020;
    public static final int turmaPeriodoLetivo = 12020;
    public static final int turmaNumeroVagas = 10;
    
    public static final AlunoId alunoId1 = AlunoId.generate();
    public static final AlunoId alunoId2 = AlunoId.generate();
    public static final AlunoId alunoId3 = AlunoId.generate();
    
    public static final DisciplinaId disciplinaId1 = DisciplinaId.generate();
    public static final DisciplinaId disciplinaId2 = DisciplinaId.generate();
    public static final DisciplinaId disciplinaId3 = DisciplinaId.generate();
    
    public static List<ProfessorId> umProfessorParaUmaDisciplina() {
        List<ProfessorId> professorId = new ArrayList<>();
        professorId.add(professorId1);
        return professorId;
    }

    public static List<ProfessorId> maisDeUmProfessorParaUmaDisciplina() {
        List<ProfessorId> professorId = new ArrayList<>();
        professorId.add(professorId1);
        professorId.add(professorId2);
        professorId.add(professorId3);
        return professorId;
    }
 
    public static List<AlunoId> maisDeUmAlunoParaUmaTurma() {
        List<AlunoId> alunoId = new ArrayList<>();
        alunoId.add(alunoId1);
        alunoId.add(alunoId2);
        alunoId.add(alunoId3);
        return alunoId;
    }

    public static List<DisciplinaId> maisDeUmaDisciplinaParaUmaTurma() {
        List<DisciplinaId> disciplinaId = new ArrayList<>();
        disciplinaId.add(disciplinaId1);
        disciplinaId.add(disciplinaId2);
        disciplinaId.add(disciplinaId3);
        return disciplinaId;
    }
    

}
