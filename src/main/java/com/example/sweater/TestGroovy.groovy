package com.example.sweater

import groovy.sql.Sql

import java.time.LocalDate

class TestGroovy {
    private static String createMessage() {
        String header = String.format("Добрый день, %s! Ваши сотрудники %t")
    }

    public static final QUERY = """
            WITH FUNCTION parse_period(p_event_date DATE, p_interval VARCHAR2) RETURN VARCHAR2
                IS
                l_result                         VARCHAR2(20 CHAR);
                l_unit                           VARCHAR2(6 CHAR);
                l_begin_time                     VARCHAR2(5 CHAR);
                TIME_INTERVAL_UNDEFINED CONSTANT VARCHAR2(32 CHAR) := 'Временной интервал не опеределен';
            BEGIN
                IF (NOT regexp_like(p_interval, '^([0-1][0-9]|2[0-3]):[0-5][0-9]\\+\\d{1,2}(h|m)\$', 'i')) THEN
                    RETURN TIME_INTERVAL_UNDEFINED;
                END IF;
            
                l_begin_time := SUBSTR(p_interval, 0, 5);
            
                l_result := TO_CHAR(p_event_date, 'DD.MM.YYYY') || ' ' || L_begin_time;
            
                CASE UPPER(SUBSTR(p_interval, -1, 1))
                    WHEN 'H' THEN l_unit := 'HOUR';
                    WHEN 'M' THEN l_unit := 'MINUTE';
                END CASE;
            
                l_result := TO_CHAR(TO_DATE(l_result, 'DD.MM.YYYY HH24:MI') +
                                    numtodsinterval(SUBSTR(p_interval, 7, length(p_interval) - 7), l_unit), 'HH24:MI');
            
                RETURN l_begin_time || ' - ' || l_result;
            END;
            SELECT USER_ID,
                   REGEXP_SUBSTR(EVENT_DESCRIPTION, '[^/]*', 1, 5)  AS operation_type,
                   REGEXP_SUBSTR(EVENT_DESCRIPTION, '[^/]*', 1, 7)  AS actual_quantity,
                   REGEXP_SUBSTR(EVENT_DESCRIPTION, '[^/]*', 1, 11) AS manager_fio,
                   REGEXP_SUBSTR(EVENT_DESCRIPTION, '[^/]*\$', 1, 1) AS manager_email,
                   parse_period(ae.EVENT_DATE, TIME_INTERVAL)       AS period_of_activity
            FROM AI_AUDIT_EVENT ae
            WHERE TRUNC(ae.EVENT_DATE) = TO_DATE('29.09.2021', 'dd.mm.yyyy')
"""

    static void main(String[] args) {
            def sql = Sql.newInstance("DB_URL_HERE", "DB_USER_HERE", "DB_PASSWORD_HERE")
            def res = sql.rows(QUERY)

            def entries = res.groupBy { it.manager_email }
                    .collectEntries {
                        [it.key,
                         it.value
                        ]
                    }

            String tableHeader = "  <thead> " + " <tr> " + " <th>Сотрудник</th>" + " <th>Операция</th>" + " <th>Выгруженных кредитов/клиентов</th>" + " <th>Интервал выгрузки</th>" + " </tr> " + " </thead> "
            String footer = ' В целях предупреждения рисков кибербезопасности и утечки конфиденциальной информации, просим проанализировать информацию о действиях сотрудников и определить их легитимность.' +
                    ' Данное письмо направлено Вам в качестве информирования. Принятие решений на основании данных сведений остается на Ваше усмотрение. Спасибо!'
            StringBuilder tableBody = new StringBuilder();

            entries.each {
                String header = String.format("Добрый день, %s! Ваши сотрудники %s совершили большое количество операций с кредитами/клиентами, нехарактерные для них:", it.value[0].MANAGER_FIO, LocalDate.now().minusDays(1).format("dd.MM.yyyy"))
            it.value.each { s ->
                tableBody
                        .append("<tr>")
                        .append("<td>").append(s.user_id).append("</td>")
                        .append("<td>").append(s.operation_type).append("</td>")
                        .append("<td>").append(s.actual_quantity).append("</td>")
                        .append("<td>").append(s.period_of_activity).append("</td>")
                        .append("</tr>")
            }

            String message = String.format("<p>%s</p><table>%s<tbody>%s</tbody></table><p>%s</p>", header, tableHeader, tableBody, footer)
            tableBody.setLength(0)
        }

    }
}
