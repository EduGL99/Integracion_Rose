package com.example.Integracion_Rose.controller;

import com.example.Integracion_Rose.models.DetailedAlarmList;
import com.example.Integracion_Rose.models.Rose;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.example.Integracion_Rose.models.GetUnsafeOkHttpClient.getUnsafeOkHttpClient;


@RestController
@RequestMapping("/tmf-api/integracionRose/v1")
public class RoseController {

    //******* PRUEBA json para crear project *******
    public String jsonCrear(){
        String jsonCrear = "{\"descriptionTT\":\"Texto de prueba funcional\",\"typeState\":\"In Progress\",\"typePriority\":\"Urgent\",\"folioClass\":\"Incidencia\",\"dateTT\":\"2022-08-22T16:15:10\",\"typeFolio\":\"Sistemas\",\"typeOrigen\":\"CCT Red\",\"typeRegion\":\"Región 7\",\"severidadInicial\":\"SF1 Crítica\",\"typeAffectation\":\"Afectación Total\",\"typeAffectationClient\":\"Con Afectación al Cliente\",\"typeClient\":\"Cliente Corporativo\",\"rootTicket\":\"Incidente relacionado adicional\",\"history\":\"Historico relacionado\",\"historical\":\"Historico relacionado detallado\",\"elements\":\"SCLmex\",\"detailedAlarmList\":[{\"idAlarma\":\"Id de la alarma\",\"managementObject\":\" M.O. de la alarma\",\"aditionalInfo\":\"Info adicional de la alarma\"},{\"idAlarma\":\"Id de la alarma2\",\"managementObject\":\" M.O. de la alarma2\",\"aditionalInfo\":\"Info adicional de la alarma2\"},{\"idAlarma\":\"Id alarma 3\",\"managementObject\":\"Alarma3 para pruebas\",\"aditionalInfo\":\"Uso de alarma 3 prueba\"}],\"user\":\"joseeduardo.garcialuna@nttdata.com\"}";
        return jsonCrear;
    }

    // crear ticket
    //@PostMapping(path="/createTroubleTicket",consumes="application/json")
    @RequestMapping(value = "/createTroubleTicket", method = RequestMethod.POST)
    public String createRose(){
        Mutations mutations = new Mutations();
        //Rose rose=new Rose();

        String jsonCrear = jsonCrear(); // verificar modificacion para prueba postman *******

        String description = "";
        String typeState = "";
        String typePriority = "";
        String folioClass = "";
        String date = "";
        //String date = rose.getDateTT();
        String typeFolio = "";
        String typeOrigen = "";
        String typeRegion = "";
        String severidadInicial = "";
        String typeAffectation = "";
        String typeAffectationClient = "";
        String typeClient = "";
        String rootTicket = "";
        String history = "";
        String historical = "";
        String elements = "";
        String user = "";

        String crearResponse="";
        try {
            JSONObject crear = new JSONObject(jsonCrear); // verificar modificacion para prueba postman *******

            description = crear.getString("descriptionTT");
            typeState = crear.getString("typeState");
            typePriority = crear.getString("typePriority").toUpperCase();
            folioClass = crear.getString("folioClass");
            //date = crear.getString("dateTT");
            typeFolio = crear.getString("typeFolio");
            typeOrigen = crear.getString("typeOrigen");
            typeRegion = crear.getString("typeRegion");
            severidadInicial = crear.getString("severidadInicial");
            typeAffectation = crear.getString("typeAffectation");
            typeAffectationClient = crear.getString("typeAffectationClient");
            typeClient = crear.getString("typeClient");
            rootTicket = crear.getString("rootTicket");
            history = crear.getString("history");
            historical = crear.getString("historical");
            elements = crear.getString("elements");
            user = crear.getString("user");

            String responseProject = mutations.crearProject(description, typeState, typePriority, folioClass, date, typeFolio, typeOrigen, typeRegion,
                    severidadInicial, typeAffectation, typeAffectationClient, typeClient, rootTicket, history, historical, elements, user);
            JSONObject responseP = new JSONObject(responseProject);

        //--------------- creacion de alarma ---------------
            String idAlarma="";
            String moAlarma="";
            String infoAlarma="";

            List listA = new ArrayList();
            int entrada = crear.getJSONArray("detailedAlarmList").length();
            for (int i=0;i<entrada;i++) {
                int id = i+1;
                String titleAlarma = "Id Alarma 0" + id;
                idAlarma = crear.getJSONArray("detailedAlarmList").getJSONObject(i).getString("idAlarma");
                moAlarma = crear.getJSONArray("detailedAlarmList").getJSONObject(i).getString("managementObject");
                infoAlarma = crear.getJSONArray("detailedAlarmList").getJSONObject(i).getString("aditionalInfo");
                String alarmas = "{\"title\":\"" + titleAlarma + "\",\"checkList\":[{\"title\":\"Id Alarma\",\"type\":\"string\",\"index\":0,\"stringValue\":\"" + idAlarma + "\"},{\"title\":\"MO Management Object\",\"type\":\"string\",\"index\":1,\"stringValue\":\"" + moAlarma + "\"},{\"title\":\"Aditional Info\",\"type\":\"string\",\"index\":2,\"stringValue\":\"" + infoAlarma + "\"}]}";
                listA.add(alarmas);
            }
            String listAlarmas = String.valueOf(listA);

            String projectId = responseP.getJSONObject("data").getJSONObject("createProject").getString("id");
            String name = "GI "+projectId;
            String priority = responseP.getJSONObject("data").getJSONObject("createProject").getString("priority");
            String checklistAlarmas = listAlarmas;
            String woAlarma = mutations.woAlarmas(projectId,name,priority,checklistAlarmas);

        //--------------- creacion de asignacion ---------------
            String userAsig = user;
            String woAsignar = mutations.woAsignar(projectId,name,priority,userAsig);

        //--------------- response de crear ---------------
            crear.put("troubleTicketId",projectId);
            crearResponse = String.valueOf(crear);
        }catch (Exception e){
            String error = "{\"400\":{\"description\":\"Bad Request\"}}";
            return error;
        }
        return crearResponse;
    }

    //******* PRUEBA json para editar *******
    public String jsonEdit(){                 //146028889364 /3  // 146028889383 --> probar este id para modificar asignacion
        String jsonEdit = "{\"troubleTicket\":\"146028889420\",\"rootTicket\":\"Incidente relacionado actualizado\",\"history\":\"Historico actualizado\",\"historical\":\"Historico detallado actualizado\",\"elements\":[{\"element\":\"6904\"},{\"element\":\"MIXSFUDEIRA01\"},{\"element\":\"RPPGABI\"}],\"detailedAlarmList\":[{\"idAlarma\":\"Id de la alarma 5\",\"managementObject\":\" M.O. de la alarma 5\",\"aditionalInfo\":\"Info adicional de la alarma 5\"},{\"idAlarma\":\"Id alarma 6\",\"managementObject\":\"Alarma 6 para pruebas\",\"aditionalInfo\":\"Uso de alarma 6 prueba\"}],\"userAsignado\":\"mguerroj@nttdata.com\",\"severity\":\"SF2 Alta\",\"bitacora\":\"Bitacora relacionada adicional de prueba\"}";
        return jsonEdit;
    }

    // modificar ticket
    @RequestMapping(value = "/modifyTroubleTicket/{projectId}", method = RequestMethod.PATCH)
    public String editRose(/*@PathVariable String projectId*/){
        Mutations mutations=new Mutations();

        String jsonEdit = jsonEdit(); // verificar modificacion para prueba postman *******

        String troubleTicket="";
        String rootTicket="";
        String history="";
        String historical="";
        String userAsig="";
        String severity="";
        String bitacora="";

        String projectId="";
        String name="";
        String editResponse="";
        try {
            JSONObject edit = new JSONObject(jsonEdit); // verificar modificacion para prueba postman *******

            troubleTicket = edit.getString("troubleTicket");
            rootTicket = edit.getString("rootTicket");
            history = edit.getString("history");
            historical = edit.getString("historical");
            userAsig = edit.getString("userAsignado");
            severity = edit.getString("severity");
            bitacora = edit.getString("bitacora");

            projectId = troubleTicket;
            name = "GI "+projectId;
            String subestado = "En Espera Respuesta Grupo Asignado"; // enviar valor despues de asignar usuario
            String upProject = mutations.editProject(projectId,name,subestado,severity,rootTicket,history,historical);

            String idWorkOrder = consultaProject(projectId);
            JSONObject wo=new JSONObject(idWorkOrder);

        //--------------- modificar elementos ---------------
            List listE=new ArrayList();
            String idWOE = wo.getJSONObject("data").getJSONObject("project").getJSONArray("workOrders").getJSONObject(0).getString("id");
            int numElement = wo.getJSONObject("data").getJSONObject("project").getJSONArray("workOrders").getJSONObject(0).getJSONArray("checkListCategories").getJSONObject(0).getJSONArray("checkList").length();

            String elementNull = wo.getJSONObject("data").getJSONObject("project").getJSONArray("workOrders").getJSONObject(0).getJSONArray("checkListCategories").getJSONObject(0).getJSONArray("checkList").getJSONObject(0).getString("stringValue");
            if(elementNull.equals("")){
                numElement=0;
            }

            for (int i=0;i<numElement;i++){
                String checkElement = wo.getJSONObject("data").getJSONObject("project").getJSONArray("workOrders").getJSONObject(0).getJSONArray("checkListCategories").getJSONObject(0).getJSONArray("checkList").getJSONObject(i).toString();
                listE.add(checkElement);
            }

            String element="";
            int entradaE = edit.getJSONArray("elements").length();
            for (int i=0;i<entradaE;i++){
                element = edit.getJSONArray("elements").getJSONObject(i).getString("element");
                int index = numElement+i;
                String elementos = "{\"title\":\"Código Elemento\",\"type\":\"string\",\"index\":"+index+",\"stringValue\":\""+element+"\"}";
                listE.add(elementos);
            }
            String listElements = String.valueOf(listE);
            String checklistElements = listElements;
            String upWoElement = mutations.upWoElementos(idWOE,name,projectId,checklistElements);

        //--------------- modificar alarmas ---------------
            List listA = new ArrayList();
            String idWOA = wo.getJSONObject("data").getJSONObject("project").getJSONArray("workOrders").getJSONObject(1).getString("id");
            int numAlarmas = wo.getJSONObject("data").getJSONObject("project").getJSONArray("workOrders").getJSONObject(1).getJSONArray("checkListCategories").length();
            for (int i=0;i<numAlarmas;i++){
                String checkAlarmas = wo.getJSONObject("data").getJSONObject("project").getJSONArray("workOrders").getJSONObject(1).getJSONArray("checkListCategories").getJSONObject(i).toString();
                listA.add(checkAlarmas);
            }

            String idAlarma="";
            String moAlarma="";
            String infoAlarma="";
            int entradaA = edit.getJSONArray("detailedAlarmList").length();
            for (int i=0;i<entradaA;i++) {
                int id = i+1+numAlarmas;
                String titleAlarma = "Id Alarma 0" + id;
                idAlarma = edit.getJSONArray("detailedAlarmList").getJSONObject(i).getString("idAlarma");
                moAlarma = edit.getJSONArray("detailedAlarmList").getJSONObject(i).getString("managementObject");
                infoAlarma = edit.getJSONArray("detailedAlarmList").getJSONObject(i).getString("aditionalInfo");
                String alarmas = "{\"title\":\"" + titleAlarma + "\",\"checkList\":[{\"title\":\"Id Alarma\",\"type\":\"string\",\"index\":0,\"stringValue\":\"" + idAlarma + "\"},{\"title\":\"MO Management Object\",\"type\":\"string\",\"index\":1,\"stringValue\":\"" + moAlarma + "\"},{\"title\":\"Aditional Info\",\"type\":\"string\",\"index\":2,\"stringValue\":\"" + infoAlarma + "\"}]}";
                listA.add(alarmas);
            }
            String listAlarmas = String.valueOf(listA);
            String checklistAlarmas = listAlarmas;
            String upWoAlarm = mutations.upWoAlarmas(idWOA,name,projectId,checklistAlarmas);

        //--------------- modificar asignacion ---------------
            userAsig = edit.getString("userAsignado");
            String idWOAs = wo.getJSONObject("data").getJSONObject("project").getJSONArray("workOrders").getJSONObject(2).getString("id");
            String woAsignar = mutations.upWOAsignar(idWOAs,name,projectId,userAsig);

        //--------------- crear/modificar solucion incidencia ---------------
            String description = bitacora;
            String priority = wo.getJSONObject("data").getJSONObject("project").getString("priority");
            String accion = "Solicitar Aprobación";
            //String userSolIn = "";
            String soluIncidencia = mutations.woSolIncidencia(name,description,priority,projectId,accion/*,userSolIn*/);

        //--------------- response de editar ---------------
            String consultaTicket = consultRose(projectId);
            JSONObject ticket=new JSONObject(consultaTicket);
            ticket.remove("user");
            ticket.remove("estado");
            ticket.remove("subestado");
            ticket.put("severity",severity);
            ticket.put("bitacora",bitacora);
            editResponse = String.valueOf(ticket);
        }catch (Exception e){
            String error = "{\"400\":{\"description\":\"Bad Request\"}}";
            return error;
        }
        return editResponse;
    }

    //******* PRUEBA json para cerrar *******
    public String jsonClose(){                 //146028889364 /3  // 146028889383   //146028889276
        String jsonClose = "{\"troubleTicket\":\"146028889383\",\"typeState\":\"Closed\",\"history\":\"Historico relacionado cerrado\",\"elementSolucion\":\"SCLmex\",\"detailedAlarmList\":[{\"idAlarma\":\"Id de la alarma cerrada\",\"managementObject\":\" M.O. de la alarma cerrada\",\"aditionalInfo\":\"Info adicional de la alarma cerrada\"}],\"user\":\"mguerroj@nttdata.com\",\"cancelData\":\"2022-08-23T17:36:56\",\"accion\":\"Cerrar Folio\",\"estado\":\"Closed\",\"subestado\":\"Falla Solucionada\"}";
        return jsonClose;
    }

    // cerrar ticket
    @RequestMapping(value = "/closeTroubleTicket/{projectId}", method = RequestMethod.PATCH)
    public String closeRose(/*@PathVariable String projectId*/){
        Mutations mutations=new Mutations();

        String jsonClose = jsonClose(); // verificar modificacion para prueba postman *******

        String troubleTicket="";
        String typeState="";
        String history="";
        String elementSolucion="";
        String user="";
        String cancelData="";
        String accion="";
        String estado="";
        String subestado="";

        String projectId="";
        String name="";
        String closeResponse="";
        try {
            JSONObject close = new JSONObject(jsonClose); // verificar modificacion para prueba postman *******

            String error = "{\"400\":{\"description\":\"Bad Request\"}}";

            troubleTicket = close.getString("troubleTicket");
            typeState = close.getString("typeState").toUpperCase();
            history = close.getString("history");
            elementSolucion = close.getString("elementSolucion");
            user = close.getString("user");
            cancelData = close.getString("cancelData");
            accion = close.getString("accion");
            estado = close.getString("estado").toUpperCase();
            subestado = close.getString("subestado");

            projectId = troubleTicket;
            name = "GI "+projectId;
            String project = consultaProject(projectId);
            JSONObject wo=new JSONObject(project);

        //--------------- modificar alarmas cerrar ---------------
            List listA = new ArrayList();
            String idWOA = wo.getJSONObject("data").getJSONObject("project").getJSONArray("workOrders").getJSONObject(1).getString("id");
            int numAlarmas = wo.getJSONObject("data").getJSONObject("project").getJSONArray("workOrders").getJSONObject(1).getJSONArray("checkListCategories").length();
            for (int i=0;i<numAlarmas;i++){
                String checkAlarmas = wo.getJSONObject("data").getJSONObject("project").getJSONArray("workOrders").getJSONObject(1).getJSONArray("checkListCategories").getJSONObject(i).toString();
                listA.add(checkAlarmas);
            }

            String idAlarma="";
            String moAlarma="";
            String infoAlarma="";
            int entradaA = close.getJSONArray("detailedAlarmList").length();
            for (int i=0;i<entradaA;i++) {
                int id = i+1+numAlarmas;
                String titleAlarma = "Id Alarma 0" + id;
                idAlarma = close.getJSONArray("detailedAlarmList").getJSONObject(i).getString("idAlarma");
                moAlarma = close.getJSONArray("detailedAlarmList").getJSONObject(i).getString("managementObject");
                infoAlarma = close.getJSONArray("detailedAlarmList").getJSONObject(i).getString("aditionalInfo");
                String alarmas = "{\"title\":\"" + titleAlarma + "\",\"checkList\":[{\"title\":\"Id Alarma\",\"type\":\"string\",\"index\":0,\"stringValue\":\"" + idAlarma + "\"},{\"title\":\"MO Management Object\",\"type\":\"string\",\"index\":1,\"stringValue\":\"" + moAlarma + "\"},{\"title\":\"Aditional Info\",\"type\":\"string\",\"index\":2,\"stringValue\":\"" + infoAlarma + "\"}]}";
                listA.add(alarmas);
            }
            String listAlarmas = String.valueOf(listA);
            String checklistAlarmas = listAlarmas;
            String upWoAlarm = mutations.upWoAlarmas(idWOA,name,projectId,checklistAlarmas);

        //--------------- modificar status a cerrar en asignar ---------------
            String idWOAs = wo.getJSONObject("data").getJSONObject("project").getJSONArray("workOrders").getJSONObject(2).getString("id");
            String status = estado;
            String cerrarAsignacion = mutations.upWOAsignarToCierre(idWOAs,name,projectId,status);

        //--------------- modificar status a cerrar en solucionar ---------------
            int ultiSoluInci = wo.getJSONObject("data").getJSONObject("project").getJSONArray("workOrders").length();
            int i = ultiSoluInci-1;
            String idWOSol = wo.getJSONObject("data").getJSONObject("project").getJSONArray("workOrders").getJSONObject(i).getString("id");
            String statusIn = estado;
            String cerrarSoluIncid = mutations.upWOSolIncidencia(idWOSol,name,projectId,statusIn);
            String bitacora = wo.getJSONObject("data").getJSONObject("project").getJSONArray("workOrders").getJSONObject(i).getString("description");

        //--------------- creacion de validar cierre ---------------
            String priority = wo.getJSONObject("data").getJSONObject("project").getString("priority");
            String fechaFinFalla = cancelData;
            String woValidar = mutations.woValidar(projectId,name,priority,accion,estado,fechaFinFalla,elementSolucion);

        //--------------- modificacion cerrar project ---------------
            String closeProject = mutations.cerrarProject(projectId,name,typeState,history,elementSolucion,user,cancelData,subestado);

        //--------------- response de editar ---------------
            String consultaTicket = consultRose(projectId);
            JSONObject ticket=new JSONObject(consultaTicket);
            ticket.remove("userAsignado");
            ticket.remove("user");
            ticket.put("elementSolucion",elementSolucion);
            ticket.put("user",user);
            ticket.put("bitacora",bitacora);
            ticket.put("cancelData",cancelData);
            ticket.put("accion",accion);
            closeResponse = String.valueOf(ticket);
        }catch (Exception e){
            String error = "{\"400\":{\"description\":\"Bad Request\"}}";
            return error;
        }
        return closeResponse;
    }

    // consulta de 1 solo id ticket
    @RequestMapping(value = "/consult/{projectId}", method = RequestMethod.GET) // /consultTroubleTicket/{troubleTicket}
    public String consultRose(@PathVariable String projectId){
        Rose rose=new Rose();

        //String projectId = "146028889372"; //"146028889383"; // "146028889167"; // "146028889092"; // "146028889030";  // valor de PRUEBA *******

        String jsonProject = consultaProject(projectId);
        String jsonAsignar = asignarFolio(projectId);
        String jsonElementos = elementos(projectId);
        String jsonAlarmas = alarmas(projectId);

        try {
            JSONObject jsonObjectProj = new JSONObject(jsonProject);
            JSONObject jsonObjectAsig = new JSONObject(jsonAsignar);
            JSONObject jsonObjectElem = new JSONObject(jsonElementos);
            JSONObject jsonObjectAlar = new JSONObject(jsonAlarmas);

            rose.setTroubleTicketId(jsonObjectProj.getJSONObject("data").getJSONObject("project").getString("id"));
            rose.setDescriptionTT(jsonObjectProj.getJSONObject("data").getJSONObject("project").getString("description"));
            rose.setTypeState(jsonObjectProj.getJSONObject("data").getJSONObject("project").getJSONArray("properties").getJSONObject(1).getString("stringValue"));
            rose.setTypePriority(jsonObjectProj.getJSONObject("data").getJSONObject("project").getString("priority"));
            rose.setFolioClass(jsonObjectProj.getJSONObject("data").getJSONObject("project").getJSONArray("properties").getJSONObject(5).getString("stringValue"));
            rose.setDateTT(jsonObjectProj.getJSONObject("data").getJSONObject("project").getJSONArray("properties").getJSONObject(3).getString("stringValue"));
            rose.setTypeFolio(jsonObjectProj.getJSONObject("data").getJSONObject("project").getJSONArray("properties").getJSONObject(0).getString("stringValue"));
            rose.setTypeOrigen(jsonObjectProj.getJSONObject("data").getJSONObject("project").getJSONArray("properties").getJSONObject(6).getString("stringValue"));
            rose.setTypeRegion(jsonObjectProj.getJSONObject("data").getJSONObject("project").getJSONArray("properties").getJSONObject(7).getString("stringValue"));
            rose.setSeveridadInicial(jsonObjectProj.getJSONObject("data").getJSONObject("project").getJSONArray("properties").getJSONObject(8).getString("stringValue"));
            rose.setTypeAffectation(jsonObjectProj.getJSONObject("data").getJSONObject("project").getJSONArray("properties").getJSONObject(9).getString("stringValue"));
            rose.setTypeAffectationClient(jsonObjectProj.getJSONObject("data").getJSONObject("project").getJSONArray("properties").getJSONObject(12).getString("stringValue"));
            rose.setTypeClient(jsonObjectProj.getJSONObject("data").getJSONObject("project").getJSONArray("properties").getJSONObject(13).getString("stringValue"));
            rose.setRootTicket(jsonObjectProj.getJSONObject("data").getJSONObject("project").getJSONArray("properties").getJSONObject(14).getString("stringValue"));
            rose.setHistory(jsonObjectProj.getJSONObject("data").getJSONObject("project").getJSONArray("properties").getJSONObject(15).getString("stringValue"));
            rose.setHistorical(jsonObjectProj.getJSONObject("data").getJSONObject("project").getJSONArray("properties").getJSONObject(16).getString("stringValue"));

            List elements = new ArrayList();
            String listelement = "";
            ObjectMapper mapElement = new ObjectMapper();
            List listE = null;

            elements.add(jsonObjectProj.getJSONObject("data").getJSONObject("project").getJSONArray("properties").getJSONObject(17).getJSONObject("nodeValue").toString().replaceAll("name","element"));
            String list = String.valueOf(elements);
            listE = mapElement.readValue(list, new TypeReference<List>() {});
            int entradaE = jsonObjectElem.getJSONObject("data").getJSONObject("workOrders").getJSONArray("edges").getJSONObject(0).getJSONObject("node").getJSONArray("checkListCategories").getJSONObject(0).getJSONArray("checkList").length();
            for (int i=0; i<entradaE; i++){
                elements.add(jsonObjectElem.getJSONObject("data").getJSONObject("workOrders").getJSONArray("edges").getJSONObject(0).getJSONObject("node").getJSONArray("checkListCategories").getJSONObject(0).getJSONArray("checkList").getJSONObject(i).toString().replaceAll("stringValue","element")); // verificar
                listelement = String.valueOf(elements);
                listE = mapElement.readValue(listelement, new TypeReference<List>() {});
            }
            rose.setElements(listE);

            List listAlarmas =new ArrayList();
            int entradaA = jsonObjectAlar.getJSONObject("data").getJSONObject("workOrders").getJSONArray("edges").getJSONObject(0).getJSONObject("node").getJSONArray("checkListCategories").length();
            for (int i = 0; i<entradaA; i++){
                listAlarmas.add(alarma(projectId,i));
            }
            rose.setDetailedAlarmList(listAlarmas);

            String user = jsonObjectProj.getJSONObject("data").getJSONObject("project").getJSONArray("properties").getJSONObject(18).getJSONObject("nodeValue").getString("name");
            Mutations m = new Mutations();
            String idUser = m.idUser(user);
            JSONObject jsonUser=new JSONObject(idUser);
            rose.setUser(jsonUser.getJSONObject("data").getJSONObject("users").getJSONArray("edges").getJSONObject(0).getJSONObject("node").getString("email"));

            rose.setUserAsignado(jsonObjectAsig.getJSONObject("data").getJSONObject("workOrders").getJSONArray("edges").getJSONObject(0).getJSONObject("node").getJSONArray("properties").getJSONObject(5).getJSONObject("nodeValue").getString("name"));
            rose.setEstado(jsonObjectProj.getJSONObject("data").getJSONObject("project").getJSONArray("properties").getJSONObject(1).getString("stringValue"));
            rose.setSubestado(jsonObjectProj.getJSONObject("data").getJSONObject("project").getJSONArray("properties").getJSONObject(2).getString("stringValue"));

            String consulatJson = new Gson().toJson(rose);
            return consulatJson;
        } catch (Exception e){
            String error = "{\"404\":{\"description\":\"Not Found\"}}";
            return error;
        }
    }

    // consulta de 1 alarma
    public DetailedAlarmList alarma(String projectId, int i){
        try {
            DetailedAlarmList dal = new DetailedAlarmList();
            String jsonAlarmas = alarmas(projectId);
            JSONObject jsonObjectAlar = new JSONObject(jsonAlarmas);
            dal.setIdAlarma(jsonObjectAlar.getJSONObject("data").getJSONObject("workOrders").getJSONArray("edges").getJSONObject(0).getJSONObject("node").getJSONArray("checkListCategories").getJSONObject(i).getJSONArray("checkList").getJSONObject(0).getString("stringValue"));
            dal.setManagementObject(jsonObjectAlar.getJSONObject("data").getJSONObject("workOrders").getJSONArray("edges").getJSONObject(0).getJSONObject("node").getJSONArray("checkListCategories").getJSONObject(i).getJSONArray("checkList").getJSONObject(1).getString("stringValue"));
            dal.setAditionalInfo(jsonObjectAlar.getJSONObject("data").getJSONObject("workOrders").getJSONArray("edges").getJSONObject(0).getJSONObject("node").getJSONArray("checkListCategories").getJSONObject(i).getJSONArray("checkList").getJSONObject(2).getString("stringValue"));
            return dal;
        } catch (Exception e){
            return null;
        }
    }

    //******* PRUEBA json para consultar tickets *******
    public String json(){
        String jsonConsulta = "[{\"troubleTicket\":\"146028889372\"},{\"troubleTicket\":\"146028889420\"},{\"troubleTicket\":\"146028889383\"}]";
        return jsonConsulta;
    }

    // consulta masiva de tickets
    @RequestMapping(value = "/consultTroubleTickets/{projectId}", method = RequestMethod.GET) //  /consultTroubleTickets/{troubleTicket}
    public String consultaMasiva(){

        String jsonConsulta = json(); // verificar modificacion para prueba postman

        List tickets = new ArrayList();
        List list = null;
        try {
            JSONArray array=new JSONArray(jsonConsulta);
            String listTickets = "";
            ObjectMapper mapper = new ObjectMapper();
            int cantidad = array.length();
            for (int i=0; i<cantidad; i++){
                String projectId = array.getJSONObject(i).getString("troubleTicket");
                tickets.add(consultRose(projectId));
                listTickets = String.valueOf(tickets);
                list = mapper.readValue(listTickets, new TypeReference<List>() {});
            }
        } catch (Exception e){
            String error = "{\"400\":{\"description\":\"Bad Request\"}}";
            return error;
        }

        List listProjects = list;
        String listtickets = new Gson().toJson(listProjects);
        return listtickets;
    }

//******************************consultas graphql***********************************************************************

    // consulta porject
    public String consultaProject(String projectId){
        try {
            OkHttpClient client = getUnsafeOkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, "{\"query\":\"query proyectRose($projectId: ID!){\\r\\n  project: node(id: $projectId){\\r\\n    ...datos\\r\\n  }\\r\\n}\\r\\n\\r\\nfragment datos on Project{\\r\\n  id\\r\\n  name\\r\\n  description\\r\\n  createTime\\r\\n  priority\\r\\n  createdBy{\\r\\n    id\\r\\n    name\\r\\n    email\\r\\n  }\\r\\n  comments{\\r\\n    author{\\r\\n      id\\r\\n      email\\r\\n      name\\r\\n    }\\r\\n    text\\r\\n    createTime\\r\\n  }\\r\\n  properties{\\r\\n    propertyType{\\r\\n      name\\r\\n    }\\r\\n    stringValue\\r\\n    nodeValue{\\r\\n      name\\r\\n    }\\r\\n  }\\r\\n  workOrders{\\r\\n    id\\r\\n    name\\r\\n    status\\r\\n    priority\\r\\n    description\\r\\n    workOrderType{\\r\\n      name\\r\\n    }\\r\\n    checkListCategories{\\r\\n      title\\r\\n      checkList{\\r\\n        title\\r\\n        stringValue\\r\\n        type\\r\\n        index\\r\\n      }\\r\\n    }\\r\\n  }\\r\\n}\",\"variables\":{\"projectId\":\""+projectId+"\"}}");
            Request request = new Request.Builder()
                    .url("https://symphony.telefonicamoviles.com.mx/graph/query")
                    .method("POST", body)
                    .addHeader("Authorization", "Basic am9zZWVkdWFyZG8uZ2FyY2lhbHVuYUBudHRkYXRhLmNvbTo2OTY5Njk2OTY5")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Cookie", "connect.sid=s%3AybAjn8Zo3JYymhtcLNTLh7598YgrX8p7.KofCyrLuMQrW%2FnS2ixAUdkN1pdQdYXKcWQ781gNisdQ")
                    .build();
            Response response = client.newCall(request).execute();
            return Objects.requireNonNull(response.body().string());
        } catch (Exception e){
            String error = "{\"500\":{\"description\":\"Internal Server Error\"}}";
            return error;
        }
    }

    // consulta GI Asignar folio
    public String asignarFolio(String projectId){
        try {
            OkHttpClient client = getUnsafeOkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, "{\"query\":\"query WorkOrderType(\\r\\n  $filters: [WorkOrderFilterInput!]!\\r\\n  $orderBy: WorkOrderOrder\\r\\n) {\\r\\n  ...WorkOrdersView_query_10glCF\\r\\n}\\r\\n\\r\\nfragment WorkOrdersView_query_10glCF on Query {\\r\\n  workOrders(orderBy: $orderBy, filterBy: $filters) {\\r\\n    edges {\\r\\n      node {\\r\\n        id\\r\\n        name\\r\\n        description\\r\\n        owner {\\r\\n          id\\r\\n          email\\r\\n        }\\r\\n        creationDate\\r\\n        status\\r\\n        properties{\\r\\n          propertyType{\\r\\n            name\\r\\n          }\\r\\n          nodeValue{\\r\\n            name\\r\\n          }\\r\\n          stringValue\\r\\n        }\\r\\n        assignedTo {\\r\\n          id\\r\\n          email\\r\\n        }\\r\\n        location {\\r\\n          name\\r\\n        }\\r\\n        workOrderType {\\r\\n          id\\r\\n          name\\r\\n        }\\r\\n        project {\\r\\n          id\\r\\n          name\\r\\n        }\\r\\n        closeDate\\r\\n        priority\\r\\n      }\\r\\n    }\\r\\n  }\\r\\n}\",\"variables\":{\"filters\":[{\"filterType\":\"WORK_ORDER_NAME\",\"operator\":\"CONTAINS\",\"stringValue\":\""+projectId+"\"},{\"filterType\":\"WORK_ORDER_TYPE\",\"operator\":\"IS_ONE_OF\",\"idSet\":[\"236223201307\"]}],\"orderBy\":{\"direction\":\"DESC\",\"field\":\"UPDATED_AT\"}}}");
            Request request = new Request.Builder()
                    .url("https://symphony.telefonicamoviles.com.mx/graph/query")
                    .method("POST", body)
                    .addHeader("Authorization", "Basic am9zZWVkdWFyZG8uZ2FyY2lhbHVuYUBudHRkYXRhLmNvbTo2OTY5Njk2OTY5")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Cookie", "connect.sid=s%3AybAjn8Zo3JYymhtcLNTLh7598YgrX8p7.KofCyrLuMQrW%2FnS2ixAUdkN1pdQdYXKcWQ781gNisdQ")
                    .build();
            Response response = client.newCall(request).execute();
            return Objects.requireNonNull(response.body().string());
        } catch (Exception e){
            String error = "{\"404\":{\"description\":\"Not Found\"}}";
            return error;
        }
    }

    // consulta GI ELementos
    public String elementos(String projectId){
        try {
            OkHttpClient client = getUnsafeOkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, "{\"query\":\"query WorkOrderElement(\\r\\n  $filters: [WorkOrderFilterInput!]!\\r\\n  $orderBy: WorkOrderOrder\\r\\n) {\\r\\n  ...WorkOrdersView_query_10glCF\\r\\n}\\r\\n\\r\\nfragment WorkOrdersView_query_10glCF on Query {\\r\\n  workOrders(orderBy: $orderBy, filterBy: $filters) {\\r\\n    edges {\\r\\n      node {\\r\\n        id\\r\\n        name\\r\\n        description\\r\\n        owner {\\r\\n          id\\r\\n          email\\r\\n        }\\r\\n        creationDate\\r\\n        status\\r\\n        assignedTo {\\r\\n          id\\r\\n          email\\r\\n        }\\r\\n        location {\\r\\n          name\\r\\n        }\\r\\n        workOrderType {\\r\\n          id\\r\\n          name\\r\\n        }\\r\\n        project {\\r\\n          id\\r\\n          name\\r\\n        }\\r\\n        closeDate\\r\\n        priority\\r\\n        checkListCategories{\\r\\n          title\\r\\n          checkList{\\r\\n            stringValue\\r\\n          }\\r\\n        }\\r\\n      }\\r\\n    }\\r\\n  }\\r\\n}\",\"variables\":{\"filters\":[{\"filterType\":\"WORK_ORDER_NAME\",\"operator\":\"CONTAINS\",\"stringValue\":\""+projectId+"\"},{\"filterType\":\"WORK_ORDER_TYPE\",\"operator\":\"IS_ONE_OF\",\"idSet\":[\"236223201316\"]}],\"orderBy\":{\"direction\":\"DESC\",\"field\":\"UPDATED_AT\"}}}");
            Request request = new Request.Builder()
                    .url("https://symphony.telefonicamoviles.com.mx/graph/query")
                    .method("POST", body)
                    .addHeader("Authorization", "Basic am9zZWVkdWFyZG8uZ2FyY2lhbHVuYUBudHRkYXRhLmNvbTo2OTY5Njk2OTY5")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Cookie", "connect.sid=s%3A2aiqb0z7g-rcG9aWVPTdd5nLsKxT4lEu.i6zjJDqYt3rZG%2BvHsyWmnVKKAEv%2BGUn6eanRb%2B74%2FS4")
                    .build();
            Response response = client.newCall(request).execute();
            return Objects.requireNonNull(response.body().string());
        } catch (Exception e){
            String error = "{\"404\":{\"description\":\"Not Found\"}}";
            return error;
        }
    }

    // consulta GI Alarmas
    public String alarmas(String projectId){
        try {
            OkHttpClient client = getUnsafeOkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, "{\"query\":\"query WorkOrderAlarm(\\r\\n  $filters: [WorkOrderFilterInput!]!\\r\\n  $orderBy: WorkOrderOrder\\r\\n) {\\r\\n  ...WorkOrdersView_query_10glCF\\r\\n}\\r\\n\\r\\nfragment WorkOrdersView_query_10glCF on Query {\\r\\n  workOrders(orderBy: $orderBy, filterBy: $filters) {\\r\\n    edges {\\r\\n      node {\\r\\n        id\\r\\n        name\\r\\n        description\\r\\n        owner {\\r\\n          id\\r\\n          email\\r\\n        }\\r\\n        creationDate\\r\\n        status\\r\\n        assignedTo {\\r\\n          id\\r\\n          email\\r\\n        }\\r\\n        location {\\r\\n          name\\r\\n        }\\r\\n        workOrderType {\\r\\n          id\\r\\n          name\\r\\n        }\\r\\n        project {\\r\\n          id\\r\\n          name\\r\\n        }\\r\\n        closeDate\\r\\n        priority\\r\\n        checkListCategories{\\r\\n          title\\r\\n          checkList{\\r\\n            stringValue\\r\\n          }\\r\\n        }\\r\\n      }\\r\\n    }\\r\\n  }\\r\\n}\",\"variables\":{\"filters\":[{\"filterType\":\"WORK_ORDER_NAME\",\"operator\":\"CONTAINS\",\"stringValue\":\""+projectId+"\"},{\"filterType\":\"WORK_ORDER_TYPE\",\"operator\":\"IS_ONE_OF\",\"idSet\":[\"236223201308\"]}],\"orderBy\":{\"direction\":\"DESC\",\"field\":\"UPDATED_AT\"}}}");
            Request request = new Request.Builder()
                    .url("https://symphony.telefonicamoviles.com.mx/graph/query")
                    .method("POST", body)
                    .addHeader("Authorization", "Basic am9zZWVkdWFyZG8uZ2FyY2lhbHVuYUBudHRkYXRhLmNvbTo2OTY5Njk2OTY5")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Cookie", "connect.sid=s%3A2aiqb0z7g-rcG9aWVPTdd5nLsKxT4lEu.i6zjJDqYt3rZG%2BvHsyWmnVKKAEv%2BGUn6eanRb%2B74%2FS4")
                    .build();
            Response response = client.newCall(request).execute();
            return Objects.requireNonNull(response.body().string());
        } catch (Exception e){
            String error = "{\"404\":{\"description\":\"Not Found\"}}";
            return error;
        }
    }


    //prueba para consulta GI Validar Cierre
    public String validarCierre(String projectId){
        try {
            OkHttpClient client = getUnsafeOkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, "{\"query\":\"query WorkOrderCierre(\\r\\n  $filters: [WorkOrderFilterInput!]!\\r\\n  $orderBy: WorkOrderOrder\\r\\n) {\\r\\n  ...WorkOrdersView_query_10glCF\\r\\n}\\r\\n\\r\\nfragment WorkOrdersView_query_10glCF on Query {\\r\\n  workOrders(orderBy: $orderBy, filterBy: $filters) {\\r\\n    edges {\\r\\n      node {\\r\\n        id\\r\\n        name\\r\\n        description\\r\\n        owner {\\r\\n          id\\r\\n          email\\r\\n        }\\r\\n        creationDate\\r\\n        status\\r\\n        assignedTo {\\r\\n          id\\r\\n          email\\r\\n        }\\r\\n        location {\\r\\n          name\\r\\n        }\\r\\n        workOrderType {\\r\\n          id\\r\\n          name\\r\\n        }\\r\\n        project {\\r\\n          id\\r\\n          name\\r\\n        }\\r\\n        closeDate\\r\\n        priority\\r\\n      }\\r\\n    }\\r\\n  }\\r\\n}\",\"variables\":{\"filters\":[{\"filterType\":\"WORK_ORDER_NAME\",\"operator\":\"CONTAINS\",\"stringValue\":\""+projectId+"\"},{\"filterType\":\"WORK_ORDER_TYPE\",\"operator\":\"IS_ONE_OF\",\"idSet\":[\"236223201313\"]}],\"orderBy\":{\"direction\":\"DESC\",\"field\":\"UPDATED_AT\"}}}");
            Request request = new Request.Builder()
                    .url("https://symphony.telefonicamoviles.com.mx/graph/query")
                    .method("POST", body)
                    .addHeader("Authorization", "Basic am9zZWVkdWFyZG8uZ2FyY2lhbHVuYUBudHRkYXRhLmNvbTo2OTY5Njk2OTY5")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Cookie", "connect.sid=s%3A2aiqb0z7g-rcG9aWVPTdd5nLsKxT4lEu.i6zjJDqYt3rZG%2BvHsyWmnVKKAEv%2BGUn6eanRb%2B74%2FS4")
                    .build();
            Response response = client.newCall(request).execute();
            return Objects.requireNonNull(response.body().string());
        } catch (Exception e){
            return null;
        }
    }

}
