package com.cherkasov.repository;

import com.cherkasov.DTO.StringDoubleDTO;
import com.cherkasov.utils.HibernateUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class SubjectRepository {

    private static SubjectRepository instance;

    private SubjectRepository() {
    }

    public static SubjectRepository getInstance() {
        if (instance == null) {
            instance = new SubjectRepository();
        }
        return instance;
    }

    public List<StringDoubleDTO> findSubjectWithAverageMark() {
        final EntityManager entityManager = HibernateUtil.getEntityManager();
        List<Object[]> list = entityManager.createNativeQuery("""
                        SELECT public.subject.name AS name, AVG(public.mark.value) AS value
                        	FROM public.mark
                         	INNER JOIN  public.subject
                         	ON public.subject.id = public.mark.subject_id
                         	WHERE public.subject.id =
                         	(SELECT public.subject.id
                         	FROM public.mark
                         	INNER JOIN public.subject
                         	ON public.subject.id = public.mark.subject_id
                         	GROUP BY public.subject.id
                         	ORDER BY AVG(public.mark.value)
                            LIMIT 1)
                         	OR public.subject.id = 
                         	(SELECT public.subject.id
                         	FROM public.mark
                         	INNER JOIN public.subject
                         	ON public.subject.id = public.mark.subject_id
                         	GROUP BY public.subject.id
                         	ORDER BY AVG(public.mark.value) DESC
                            LIMIT 1)
                         	GROUP BY public.subject.name
                            ORDER BY AVG(public.mark.value);""")
                .getResultList();
        return list.stream()
                .map(objects -> {
                    StringDoubleDTO subjectWithMark = new StringDoubleDTO();
                    subjectWithMark.setName((String) objects[0]);
                    BigDecimal bd = (BigDecimal) objects[1];
                    subjectWithMark.setValue(bd.doubleValue());
                    return subjectWithMark;
                })
                .toList();
    }
}
