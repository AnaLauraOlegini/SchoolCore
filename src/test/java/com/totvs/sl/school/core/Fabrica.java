package com.totvs.sl.school.core;

import java.util.ArrayList;
import java.util.List;

import com.totvs.sl.school.core.aluno.domain.model.AlunoId;
import com.totvs.sl.school.core.disciplina.domain.enums.Disciplinas;
import com.totvs.sl.school.core.disciplina.domain.model.DisciplinaId;
import com.totvs.sl.school.core.professor.domain.model.ProfessorId;

public class Fabrica {

	public static final ProfessorId professorId1 = ProfessorId.generate();
	public static final ProfessorId professorId2 = ProfessorId.generate();
	public static final ProfessorId professorId3 = ProfessorId.generate();

	public static final String professorNome1 = "Professor 1";
	public static final String professorCpf1 = "33699891909";
	public static final String professorEmail1 = "professor1@gmail.com";
	public static final String professorTitulacao1 = "PHD";
	
	public static final String professorNome2 = "Professor 2";
	public static final String professorCpf2 = "59637547800";
	public static final String professorEmail2 = "professor2@gmail.com";
	public static final String professorTitulacao2 = "Mestre";
	
	public static final String professorNome3 = "Professor 3";
	public static final String professorCpf3 = "596375478";
	public static final String professorEmail3 = "professor3@gmail.com";
	public static final String professorTitulacao3 = "Mestre";

	public static final AlunoId alunoId1 = AlunoId.generate();
	public static final AlunoId alunoId2 = AlunoId.generate();
	public static final AlunoId alunoId3 = AlunoId.generate();
	
	public static final String alunoNome1 = "Aluno 1";
	public static final String alunoCpf1 = "11624668046";
	public static final String alunoEmail1 = "aluno1@gmail.com";
	public static final String alunoFormaIngresso1 = "ENADE";
	public static final int alunoMatricula1 = 100001;
	
	public static final String alunoNome2 = "Aluno 2";
	public static final String alunoCpf2 = "33511897963";
	public static final String alunoEmail2 = "aluno2@gmail.com";
	public static final String alunoFormaIngresso2 = "Vestibular";
	public static final int alunoMatricula2 = 100002;
	
	public static final String alunoNome3 = "Aluno 3";
	public static final String alunoCpf3 = "306398289";
	public static final String alunoEmail3 = "aluno3@gmail.com";
	public static final String alunoFormaIngresso3 = "Vestibular";
	public static final int alunoMatricula3 = 100003;
	
	public static final DisciplinaId disciplinaId1 = DisciplinaId.generate();
	public static final DisciplinaId disciplinaId2 = DisciplinaId.generate();
	public static final DisciplinaId disciplinaId3 = DisciplinaId.generate();
	
	public static final String disciplinaDescricao1 = "Português";
	public static final Disciplinas disciplinaSigla1 = Disciplinas.POR;
	public static final int disciplinaCargaHoraria1 = 3;

	public static final String disciplinaDescricao2 = "Matemática";
	public static final Disciplinas disciplinaSigla2 = Disciplinas.MAT;
	public static final int disciplinaCargaHoraria2 = 4;

	public static final String turmaDescricao1 = "Turma I";
	public static final int turmaAnoLetivo1 = 2020;
	public static final int turmaPeriodoLetivo1 = 12020;
	public static final int turmaNumeroVagas1 = 10;

	public static List<ProfessorId> umProfessorParaUmaDisciplina() {
		List<ProfessorId> professorId = new ArrayList<>();
		professorId.add(professorId1);
		return professorId;
	}
	
	public static List<AlunoId> criarUmaTurmaComAlunos() {
		List<AlunoId> alunoId = new ArrayList<>();
		alunoId.add(alunoId1);
		alunoId.add(alunoId2);
		alunoId.add(alunoId3);
		return alunoId;
	}

	public static List<DisciplinaId> criarUmaTurmaComDisciplinas() {
		List<DisciplinaId> disciplinaId = new ArrayList<>();
		disciplinaId.add(disciplinaId1);
		disciplinaId.add(disciplinaId2);
		disciplinaId.add(disciplinaId3);
		return disciplinaId;
	}
	
	public static List<String> maisDeUmProfessorParaUmaDisciplina() {
		List<String> professorId = new ArrayList<>();
		professorId.add(professorId1.toString());
		//professorId.add(professorId2.toString());
		//professorId.add(professorId3.toString());
		return professorId;
	}

	public static List<String> maisDeUmAlunoParaUmaTurma() {
		List<String> alunoId = new ArrayList<>();
		alunoId.add(alunoId1.toString());
		alunoId.add(alunoId2.toString());
		alunoId.add(alunoId3.toString());
		return alunoId;
	}

	public static List<String> maisDeUmaDisciplinaParaUmaTurma() {
		List<String> disciplinaId = new ArrayList<>();
		disciplinaId.add(disciplinaId1.toString());
		disciplinaId.add(disciplinaId2.toString());
		disciplinaId.add(disciplinaId3.toString());
		return disciplinaId;
	}
	
}
