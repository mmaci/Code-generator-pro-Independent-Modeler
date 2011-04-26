<?xml version="1.0" encoding="UTF-8"?> 
<java version="1.6.0_14" class="java.beans.XMLDecoder"> 
 <object class="cz.cvut.indepmod.classmodel.diagramdata.DiagramDataModel"> 
  <object class="org.jgraph.graph.GraphLayoutCache"> 
   <object class="cz.cvut.indepmod.classmodel.workspace.ClassModelGraphModel"> 
    <object class="java.util.ArrayList"> 
     <void method="add"> 
      <object id="ClassModelClassCell0" class="cz.cvut.indepmod.classmodel.workspace.cell.ClassModelClassCell"> 
       <object class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel"> 
        <string>Class1</string> 
        <void method="addMethod"> 
         <object class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.MethodModel"> 
          <object id="ClassModel0" class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel"> 
           <string>Class2</string> 
           <void method="addAttribute"> 
            <object class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AttributeModel"> 
             <object idref="ClassModel0"/> 
             <string>atr2</string> 
             <object id="Visibility$40" class="cz.cvut.indepmod.classmodel.api.model.Visibility$4" method="valueOf"> 
              <string>PRIVATE</string> 
             </object> 
            </object> 
           </void> 
           <void property="stereotype"> 
            <null/> 
           </void> 
          </object> 
          <string>metoda11</string> 
          <object class="java.util.HashSet"/> 
          <object class="cz.cvut.indepmod.classmodel.api.model.Visibility$1" method="valueOf"> 
           <string>NONE</string> 
          </object> 
         </object> 
        </void> 
        <void method="addAttribute"> 
         <object class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AttributeModel"> 
          <object idref="ClassModel0"/> 
          <string>atr11</string> 
          <object class="cz.cvut.indepmod.classmodel.api.model.Visibility$3" method="valueOf"> 
           <string>PROTECTED</string> 
          </object> 
          <void method="addAnotation"> 
           <object class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AnotationModel"> 
            <string>anot11</string> 
            <void method="addAttribute"> 
             <object class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AnotationAttributeModel"> 
              <string>a</string> 
              <void method="addValue"> 
               <string>1</string> 
              </void> 
             </object> 
            </void> 
           </object> 
          </void> 
         </object> 
        </void> 
        <void property="stereotype"> 
         <null/> 
        </void> 
       </object> 
       <void method="add"> 
        <object id="DefaultPort0" class="org.jgraph.graph.DefaultPort"> 
         <void id="HashSet0" property="edges"> 
          <void method="add"> 
           <object id="ClassModelRelation0" class="cz.cvut.indepmod.classmodel.workspace.cell.ClassModelRelation"> 
            <void property="attributes"> 
             <void method="put"> 
              <string>labelAlongEdge</string> 
              <boolean>true</boolean> 
             </void> 
             <void method="put"> 
              <string>disconnectable</string> 
              <boolean>false</boolean> 
             </void> 
             <void method="put"> 
              <string>moveable</string> 
              <boolean>true</boolean> 
             </void> 
             <void method="put"> 
              <string>labelposition</string> 
              <object class="org.jgraph.graph.AttributeMap$SerializablePoint2D"> 
               <void property="x"> 
                <double>500.0</double> 
               </void> 
               <void property="y"> 
                <double>10.0</double> 
               </void> 
              </object> 
             </void> 
             <void method="put"> 
              <string>lineStyle</string> 
              <int>11</int> 
             </void> 
             <void method="put"> 
              <string>extraLabels</string> 
              <array class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.Cardinality" length="2"> 
               <void index="0"> 
                <object id="Cardinality0" class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.Cardinality"> 
                 <int>1</int> 
                 <int>1</int> 
                </object> 
               </void> 
               <void index="1"> 
                <object idref="Cardinality0"/> 
               </void> 
              </array> 
             </void> 
             <void method="put"> 
              <string>editable</string> 
              <boolean>false</boolean> 
             </void> 
             <void method="put"> 
              <string>extraLabelPositions</string> 
              <array class="java.awt.geom.Point2D" length="2"> 
               <void index="0"> 
                <object class="org.jgraph.graph.AttributeMap$SerializablePoint2D"> 
                 <void property="x"> 
                  <double>125.0</double> 
                 </void> 
                 <void property="y"> 
                  <double>20.0</double> 
                 </void> 
                </object> 
               </void> 
               <void index="1"> 
                <object class="org.jgraph.graph.AttributeMap$SerializablePoint2D"> 
                 <void property="x"> 
                  <double>875.0</double> 
                 </void> 
                 <void property="y"> 
                  <double>20.0</double> 
                 </void> 
                </object> 
               </void> 
              </array> 
             </void> 
            </void> 
            <void property="source"> 
             <object idref="DefaultPort0"/> 
            </void> 
            <void property="target"> 
             <object id="DefaultPort1" class="org.jgraph.graph.DefaultPort"> 
              <void id="HashSet1" property="edges"> 
               <void method="add"> 
                <object id="ClassModelRelation1" class="cz.cvut.indepmod.classmodel.workspace.cell.ClassModelRelation"> 
                 <void property="attributes"> 
                  <void method="put"> 
                   <string>labelAlongEdge</string> 
                   <boolean>true</boolean> 
                  </void> 
                  <void method="put"> 
                   <string>disconnectable</string> 
                   <boolean>false</boolean> 
                  </void> 
                  <void method="put"> 
                   <string>moveable</string> 
                   <boolean>true</boolean> 
                  </void> 
                  <void method="put"> 
                   <string>beginSize</string> 
                   <int>20</int> 
                  </void> 
                  <void method="put"> 
                   <string>lineBegin</string> 
                   <int>9</int> 
                  </void> 
                  <void method="put"> 
                   <string>labelposition</string> 
                   <object class="org.jgraph.graph.AttributeMap$SerializablePoint2D"> 
                    <void property="x"> 
                     <double>500.0</double> 
                    </void> 
                    <void property="y"> 
                     <double>10.0</double> 
                    </void> 
                   </object> 
                  </void> 
                  <void method="put"> 
                   <string>lineStyle</string> 
                   <int>11</int> 
                  </void> 
                  <void method="put"> 
                   <string>extraLabels</string> 
                   <array class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.Cardinality" length="2"> 
                    <void index="0"> 
                     <object idref="Cardinality0"/> 
                    </void> 
                    <void index="1"> 
                     <object idref="Cardinality0"/> 
                    </void> 
                   </array> 
                  </void> 
                  <void method="put"> 
                   <string>beginFill</string> 
                   <boolean>true</boolean> 
                  </void> 
                  <void method="put"> 
                   <string>editable</string> 
                   <boolean>false</boolean> 
                  </void> 
                  <void method="put"> 
                   <string>extraLabelPositions</string> 
                   <array class="java.awt.geom.Point2D" length="2"> 
                    <void index="0"> 
                     <object class="org.jgraph.graph.AttributeMap$SerializablePoint2D"> 
                      <void property="x"> 
                       <double>125.0</double> 
                      </void> 
                      <void property="y"> 
                       <double>20.0</double> 
                      </void> 
                     </object> 
                    </void> 
                    <void index="1"> 
                     <object class="org.jgraph.graph.AttributeMap$SerializablePoint2D"> 
                      <void property="x"> 
                       <double>875.0</double> 
                      </void> 
                      <void property="y"> 
                       <double>20.0</double> 
                      </void> 
                     </object> 
                    </void> 
                   </array> 
                  </void> 
                 </void> 
                 <void property="source"> 
                  <object id="DefaultPort2" class="org.jgraph.graph.DefaultPort"> 
                   <void id="HashSet2" property="edges"> 
                    <void method="add"> 
                     <object id="ClassModelRelation2" class="cz.cvut.indepmod.classmodel.workspace.cell.ClassModelRelation"> 
                      <void property="attributes"> 
                       <void method="put"> 
                        <string>dashPattern</string> 
                        <array class="float" length="2"> 
                         <void index="0"> 
                          <float>10.0</float> 
                         </void> 
                         <void index="1"> 
                          <float>5.0</float> 
                         </void> 
                        </array> 
                       </void> 
                       <void method="put"> 
                        <string>labelAlongEdge</string> 
                        <boolean>true</boolean> 
                       </void> 
                       <void method="put"> 
                        <string>disconnectable</string> 
                        <boolean>false</boolean> 
                       </void> 
                       <void method="put"> 
                        <string>lineEnd</string> 
                        <int>2</int> 
                       </void> 
                       <void method="put"> 
                        <string>moveable</string> 
                        <boolean>true</boolean> 
                       </void> 
                       <void method="put"> 
                        <string>labelposition</string> 
                        <object class="org.jgraph.graph.AttributeMap$SerializablePoint2D"> 
                         <void property="x"> 
                          <double>500.0</double> 
                         </void> 
                         <void property="y"> 
                          <double>10.0</double> 
                         </void> 
                        </object> 
                       </void> 
                       <void method="put"> 
                        <string>lineStyle</string> 
                        <int>11</int> 
                       </void> 
                       <void method="put"> 
                        <string>endFill</string> 
                        <boolean>false</boolean> 
                       </void> 
                       <void method="put"> 
                        <string>editable</string> 
                        <boolean>false</boolean> 
                       </void> 
                      </void> 
                      <void property="source"> 
                       <object idref="DefaultPort2"/> 
                      </void> 
                      <void property="target"> 
                       <object id="DefaultPort3" class="org.jgraph.graph.DefaultPort"> 
                        <void id="HashSet3" property="edges"> 
                         <void method="add"> 
                          <object idref="ClassModelRelation2"/> 
                         </void> 
                        </void> 
                        <void property="edges"> 
                         <object idref="HashSet3"/> 
                        </void> 
                       </object> 
                      </void> 
                      <void property="userObject"> 
                       <object class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.HierarchyRelationModel"> 
                        <object class="cz.cvut.indepmod.classmodel.api.model.RelationType" method="valueOf"> 
                         <string>REALISATION</string> 
                        </object> 
                        <void property="cell"> 
                         <object idref="ClassModelRelation2"/> 
                        </void> 
                       </object> 
                      </void> 
                     </object> 
                    </void> 
                    <void method="add"> 
                     <object id="ClassModelRelation3" class="cz.cvut.indepmod.classmodel.workspace.cell.ClassModelRelation"> 
                      <void property="attributes"> 
                       <void method="put"> 
                        <string>labelAlongEdge</string> 
                        <boolean>true</boolean> 
                       </void> 
                       <void method="put"> 
                        <string>disconnectable</string> 
                        <boolean>false</boolean> 
                       </void> 
                       <void method="put"> 
                        <string>lineEnd</string> 
                        <int>2</int> 
                       </void> 
                       <void method="put"> 
                        <string>moveable</string> 
                        <boolean>true</boolean> 
                       </void> 
                       <void method="put"> 
                        <string>labelposition</string> 
                        <object class="org.jgraph.graph.AttributeMap$SerializablePoint2D"> 
                         <void property="x"> 
                          <double>500.0</double> 
                         </void> 
                         <void property="y"> 
                          <double>10.0</double> 
                         </void> 
                        </object> 
                       </void> 
                       <void method="put"> 
                        <string>lineStyle</string> 
                        <int>11</int> 
                       </void> 
                       <void method="put"> 
                        <string>endFill</string> 
                        <boolean>false</boolean> 
                       </void> 
                       <void method="put"> 
                        <string>editable</string> 
                        <boolean>false</boolean> 
                       </void> 
                      </void> 
                      <void property="source"> 
                       <object idref="DefaultPort2"/> 
                      </void> 
                      <void property="target"> 
                       <object id="DefaultPort4" class="org.jgraph.graph.DefaultPort"> 
                        <void id="HashSet4" property="edges"> 
                         <void method="add"> 
                          <object idref="ClassModelRelation3"/> 
                         </void> 
                         <void method="add"> 
                          <object id="ClassModelRelation4" class="cz.cvut.indepmod.classmodel.workspace.cell.ClassModelRelation"> 
                           <void property="attributes"> 
                            <void method="put"> 
                             <string>labelAlongEdge</string> 
                             <boolean>true</boolean> 
                            </void> 
                            <void method="put"> 
                             <string>disconnectable</string> 
                             <boolean>false</boolean> 
                            </void> 
                            <void method="put"> 
                             <string>lineEnd</string> 
                             <int>2</int> 
                            </void> 
                            <void method="put"> 
                             <string>moveable</string> 
                             <boolean>true</boolean> 
                            </void> 
                            <void method="put"> 
                             <string>labelposition</string> 
                             <object class="org.jgraph.graph.AttributeMap$SerializablePoint2D"> 
                              <void property="x"> 
                               <double>500.0</double> 
                              </void> 
                              <void property="y"> 
                               <double>10.0</double> 
                              </void> 
                             </object> 
                            </void> 
                            <void method="put"> 
                             <string>lineStyle</string> 
                             <int>11</int> 
                            </void> 
                            <void method="put"> 
                             <string>endFill</string> 
                             <boolean>false</boolean> 
                            </void> 
                            <void method="put"> 
                             <string>editable</string> 
                             <boolean>false</boolean> 
                            </void> 
                           </void> 
                           <void property="source"> 
                            <object id="DefaultPort5" class="org.jgraph.graph.DefaultPort"> 
                             <void id="HashSet5" property="edges"> 
                              <void method="add"> 
                               <object idref="ClassModelRelation4"/> 
                              </void> 
                              <void method="add"> 
                               <object id="ClassModelRelation5" class="cz.cvut.indepmod.classmodel.workspace.cell.ClassModelRelation"> 
                                <void property="attributes"> 
                                 <void method="put"> 
                                  <string>labelAlongEdge</string> 
                                  <boolean>true</boolean> 
                                 </void> 
                                 <void method="put"> 
                                  <string>disconnectable</string> 
                                  <boolean>false</boolean> 
                                 </void> 
                                 <void method="put"> 
                                  <string>moveable</string> 
                                  <boolean>true</boolean> 
                                 </void> 
                                 <void method="put"> 
                                  <string>labelposition</string> 
                                  <object class="org.jgraph.graph.AttributeMap$SerializablePoint2D"> 
                                   <void property="x"> 
                                    <double>500.0</double> 
                                   </void> 
                                   <void property="y"> 
                                    <double>10.0</double> 
                                   </void> 
                                  </object> 
                                 </void> 
                                 <void method="put"> 
                                  <string>lineStyle</string> 
                                  <int>11</int> 
                                 </void> 
                                 <void method="put"> 
                                  <string>extraLabels</string> 
                                  <array class="cz.cvut.indepmod.classmodel.api.model.ICardinality" length="2"> 
                                   <void index="0"> 
                                    <object id="Cardinality1" class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.Cardinality"> 
                                     <int>0</int> 
                                     <int>-1</int> 
                                    </object> 
                                   </void> 
                                   <void index="1"> 
                                    <object idref="Cardinality0"/> 
                                   </void> 
                                  </array> 
                                 </void> 
                                 <void method="put"> 
                                  <string>editable</string> 
                                  <boolean>false</boolean> 
                                 </void> 
                                 <void method="put"> 
                                  <string>extraLabelPositions</string> 
                                  <array class="java.awt.geom.Point2D" length="2"> 
                                   <void index="0"> 
                                    <object class="org.jgraph.graph.AttributeMap$SerializablePoint2D"> 
                                     <void property="x"> 
                                      <double>125.0</double> 
                                     </void> 
                                     <void property="y"> 
                                      <double>20.0</double> 
                                     </void> 
                                    </object> 
                                   </void> 
                                   <void index="1"> 
                                    <object class="org.jgraph.graph.AttributeMap$SerializablePoint2D"> 
                                     <void property="x"> 
                                      <double>875.0</double> 
                                     </void> 
                                     <void property="y"> 
                                      <double>20.0</double> 
                                     </void> 
                                    </object> 
                                   </void> 
                                  </array> 
                                 </void> 
                                </void> 
                                <void property="source"> 
                                 <object idref="DefaultPort2"/> 
                                </void> 
                                <void property="target"> 
                                 <object idref="DefaultPort5"/> 
                                </void> 
                                <void property="userObject"> 
                                 <object class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.RelationModel"> 
                                  <object id="RelationType0" class="cz.cvut.indepmod.classmodel.api.model.RelationType" method="valueOf"> 
                                   <string>RELATION</string> 
                                  </object> 
                                  <void property="cell"> 
                                   <object idref="ClassModelRelation5"/> 
                                  </void> 
                                 </object> 
                                </void> 
                               </object> 
                              </void> 
                              <void method="add"> 
                               <object id="ClassModelRelation6" class="cz.cvut.indepmod.classmodel.workspace.cell.ClassModelRelation"> 
                                <void property="attributes"> 
                                 <void method="put"> 
                                  <string>labelAlongEdge</string> 
                                  <boolean>true</boolean> 
                                 </void> 
                                 <void method="put"> 
                                  <string>disconnectable</string> 
                                  <boolean>false</boolean> 
                                 </void> 
                                 <void method="put"> 
                                  <string>moveable</string> 
                                  <boolean>true</boolean> 
                                 </void> 
                                 <void method="put"> 
                                  <string>beginSize</string> 
                                  <int>20</int> 
                                 </void> 
                                 <void method="put"> 
                                  <string>lineBegin</string> 
                                  <int>9</int> 
                                 </void> 
                                 <void method="put"> 
                                  <string>labelposition</string> 
                                  <object class="org.jgraph.graph.AttributeMap$SerializablePoint2D"> 
                                   <void property="x"> 
                                    <double>500.0</double> 
                                   </void> 
                                   <void property="y"> 
                                    <double>10.0</double> 
                                   </void> 
                                  </object> 
                                 </void> 
                                 <void method="put"> 
                                  <string>lineStyle</string> 
                                  <int>11</int> 
                                 </void> 
                                 <void method="put"> 
                                  <string>extraLabels</string> 
                                  <array class="cz.cvut.indepmod.classmodel.api.model.ICardinality" length="2"> 
                                   <void index="0"> 
                                    <object idref="Cardinality0"/> 
                                   </void> 
                                   <void index="1"> 
                                    <object idref="Cardinality1"/> 
                                   </void> 
                                  </array> 
                                 </void> 
                                 <void method="put"> 
                                  <string>beginFill</string> 
                                  <boolean>false</boolean> 
                                 </void> 
                                 <void method="put"> 
                                  <string>editable</string> 
                                  <boolean>false</boolean> 
                                 </void> 
                                 <void method="put"> 
                                  <string>extraLabelPositions</string> 
                                  <array class="java.awt.geom.Point2D" length="2"> 
                                   <void index="0"> 
                                    <object class="org.jgraph.graph.AttributeMap$SerializablePoint2D"> 
                                     <void property="x"> 
                                      <double>125.0</double> 
                                     </void> 
                                     <void property="y"> 
                                      <double>20.0</double> 
                                     </void> 
                                    </object> 
                                   </void> 
                                   <void index="1"> 
                                    <object class="org.jgraph.graph.AttributeMap$SerializablePoint2D"> 
                                     <void property="x"> 
                                      <double>875.0</double> 
                                     </void> 
                                     <void property="y"> 
                                      <double>20.0</double> 
                                     </void> 
                                    </object> 
                                   </void> 
                                  </array> 
                                 </void> 
                                </void> 
                                <void property="source"> 
                                 <object idref="DefaultPort5"/> 
                                </void> 
                                <void property="target"> 
                                 <object id="DefaultPort6" class="org.jgraph.graph.DefaultPort"> 
                                  <void id="HashSet6" property="edges"> 
                                   <void method="add"> 
                                    <object idref="ClassModelRelation6"/> 
                                   </void> 
                                  </void> 
                                  <void property="edges"> 
                                   <object idref="HashSet6"/> 
                                  </void> 
                                 </object> 
                                </void> 
                                <void property="userObject"> 
                                 <object class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.RelationModel"> 
                                  <object class="cz.cvut.indepmod.classmodel.api.model.RelationType" method="valueOf"> 
                                   <string>AGREGATION</string> 
                                  </object> 
                                  <void property="cell"> 
                                   <object idref="ClassModelRelation6"/> 
                                  </void> 
                                 </object> 
                                </void> 
                               </object> 
                              </void> 
                             </void> 
                             <void property="edges"> 
                              <object idref="HashSet5"/> 
                             </void> 
                            </object> 
                           </void> 
                           <void property="target"> 
                            <object idref="DefaultPort4"/> 
                           </void> 
                           <void property="userObject"> 
                            <object class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.HierarchyRelationModel"> 
                             <object id="RelationType1" class="cz.cvut.indepmod.classmodel.api.model.RelationType" method="valueOf"> 
                              <string>GENERALIZATION</string> 
                             </object> 
                             <void property="cell"> 
                              <object idref="ClassModelRelation4"/> 
                             </void> 
                            </object> 
                           </void> 
                          </object> 
                         </void> 
                        </void> 
                        <void property="edges"> 
                         <object idref="HashSet4"/> 
                        </void> 
                       </object> 
                      </void> 
                      <void property="userObject"> 
                       <object class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.HierarchyRelationModel"> 
                        <object idref="RelationType1"/> 
                        <void property="cell"> 
                         <object idref="ClassModelRelation3"/> 
                        </void> 
                       </object> 
                      </void> 
                     </object> 
                    </void> 
                    <void method="add"> 
                     <object idref="ClassModelRelation1"/> 
                    </void> 
                    <void method="add"> 
                     <object id="ClassModelRelation7" class="cz.cvut.indepmod.classmodel.workspace.cell.ClassModelRelation"> 
                      <void property="attributes"> 
                       <void method="put"> 
                        <string>labelAlongEdge</string> 
                        <boolean>true</boolean> 
                       </void> 
                       <void method="put"> 
                        <string>disconnectable</string> 
                        <boolean>false</boolean> 
                       </void> 
                       <void method="put"> 
                        <string>moveable</string> 
                        <boolean>true</boolean> 
                       </void> 
                       <void method="put"> 
                        <string>labelposition</string> 
                        <object class="org.jgraph.graph.AttributeMap$SerializablePoint2D"> 
                         <void property="x"> 
                          <double>500.0</double> 
                         </void> 
                         <void property="y"> 
                          <double>10.0</double> 
                         </void> 
                        </object> 
                       </void> 
                       <void method="put"> 
                        <string>lineStyle</string> 
                        <int>11</int> 
                       </void> 
                       <void method="put"> 
                        <string>extraLabels</string> 
                        <array class="cz.cvut.indepmod.classmodel.api.model.ICardinality" length="2"> 
                         <void index="0"> 
                          <object idref="Cardinality0"/> 
                         </void> 
                         <void index="1"> 
                          <object idref="Cardinality1"/> 
                         </void> 
                        </array> 
                       </void> 
                       <void method="put"> 
                        <string>editable</string> 
                        <boolean>false</boolean> 
                       </void> 
                       <void method="put"> 
                        <string>extraLabelPositions</string> 
                        <array class="java.awt.geom.Point2D" length="2"> 
                         <void index="0"> 
                          <object class="org.jgraph.graph.AttributeMap$SerializablePoint2D"> 
                           <void property="x"> 
                            <double>125.0</double> 
                           </void> 
                           <void property="y"> 
                            <double>20.0</double> 
                           </void> 
                          </object> 
                         </void> 
                         <void index="1"> 
                          <object class="org.jgraph.graph.AttributeMap$SerializablePoint2D"> 
                           <void property="x"> 
                            <double>875.0</double> 
                           </void> 
                           <void property="y"> 
                            <double>20.0</double> 
                           </void> 
                          </object> 
                         </void> 
                        </array> 
                       </void> 
                      </void> 
                      <void property="source"> 
                       <object idref="DefaultPort0"/> 
                      </void> 
                      <void property="target"> 
                       <object idref="DefaultPort2"/> 
                      </void> 
                      <void property="userObject"> 
                       <object class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.RelationModel"> 
                        <object idref="RelationType0"/> 
                        <void property="cell"> 
                         <object idref="ClassModelRelation7"/> 
                        </void> 
                       </object> 
                      </void> 
                     </object> 
                    </void> 
                    <void method="add"> 
                     <object idref="ClassModelRelation5"/> 
                    </void> 
                   </void> 
                   <void property="edges"> 
                    <object idref="HashSet2"/> 
                   </void> 
                  </object> 
                 </void> 
                 <void property="target"> 
                  <object idref="DefaultPort1"/> 
                 </void> 
                 <void property="userObject"> 
                  <object class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.RelationModel"> 
                   <object id="RelationType2" class="cz.cvut.indepmod.classmodel.api.model.RelationType" method="valueOf"> 
                    <string>COMPOSITION</string> 
                   </object> 
                   <void property="cell"> 
                    <object idref="ClassModelRelation1"/> 
                   </void> 
                  </object> 
                 </void> 
                </object> 
               </void> 
               <void method="add"> 
                <object idref="ClassModelRelation0"/> 
               </void> 
              </void> 
              <void property="edges"> 
               <object idref="HashSet1"/> 
              </void> 
             </object> 
            </void> 
            <void property="userObject"> 
             <object class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.RelationModel"> 
              <object idref="RelationType0"/> 
              <void property="cell"> 
               <object idref="ClassModelRelation0"/> 
              </void> 
             </object> 
            </void> 
           </object> 
          </void> 
          <void method="add"> 
           <object idref="ClassModelRelation7"/> 
          </void> 
          <void method="add"> 
           <object id="ClassModelRelation8" class="cz.cvut.indepmod.classmodel.workspace.cell.ClassModelRelation"> 
            <void property="attributes"> 
             <void method="put"> 
              <string>labelAlongEdge</string> 
              <boolean>true</boolean> 
             </void> 
             <void method="put"> 
              <string>disconnectable</string> 
              <boolean>false</boolean> 
             </void> 
             <void method="put"> 
              <string>moveable</string> 
              <boolean>true</boolean> 
             </void> 
             <void method="put"> 
              <string>beginSize</string> 
              <int>20</int> 
             </void> 
             <void method="put"> 
              <string>lineBegin</string> 
              <int>9</int> 
             </void> 
             <void method="put"> 
              <string>labelposition</string> 
              <object class="org.jgraph.graph.AttributeMap$SerializablePoint2D"> 
               <void property="x"> 
                <double>500.0</double> 
               </void> 
               <void property="y"> 
                <double>10.0</double> 
               </void> 
              </object> 
             </void> 
             <void method="put"> 
              <string>lineStyle</string> 
              <int>11</int> 
             </void> 
             <void method="put"> 
              <string>extraLabels</string> 
              <array class="cz.cvut.indepmod.classmodel.api.model.ICardinality" length="2"> 
               <void index="0"> 
                <object idref="Cardinality0"/> 
               </void> 
               <void index="1"> 
                <object idref="Cardinality1"/> 
               </void> 
              </array> 
             </void> 
             <void method="put"> 
              <string>beginFill</string> 
              <boolean>true</boolean> 
             </void> 
             <void method="put"> 
              <string>editable</string> 
              <boolean>false</boolean> 
             </void> 
             <void method="put"> 
              <string>extraLabelPositions</string> 
              <array class="java.awt.geom.Point2D" length="2"> 
               <void index="0"> 
                <object class="org.jgraph.graph.AttributeMap$SerializablePoint2D"> 
                 <void property="x"> 
                  <double>125.0</double> 
                 </void> 
                 <void property="y"> 
                  <double>20.0</double> 
                 </void> 
                </object> 
               </void> 
               <void index="1"> 
                <object class="org.jgraph.graph.AttributeMap$SerializablePoint2D"> 
                 <void property="x"> 
                  <double>875.0</double> 
                 </void> 
                 <void property="y"> 
                  <double>20.0</double> 
                 </void> 
                </object> 
               </void> 
              </array> 
             </void> 
            </void> 
            <void property="source"> 
             <object idref="DefaultPort0"/> 
            </void> 
            <void property="target"> 
             <object id="DefaultPort7" class="org.jgraph.graph.DefaultPort"> 
              <void id="HashSet7" property="edges"> 
               <void method="add"> 
                <object idref="ClassModelRelation8"/> 
               </void> 
              </void> 
              <void property="edges"> 
               <object idref="HashSet7"/> 
              </void> 
             </object> 
            </void> 
            <void property="userObject"> 
             <object class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.RelationModel"> 
              <object idref="RelationType2"/> 
              <void property="cell"> 
               <object idref="ClassModelRelation8"/> 
              </void> 
             </object> 
            </void> 
           </object> 
          </void> 
         </void> 
         <void property="edges"> 
          <object idref="HashSet0"/> 
         </void> 
        </object> 
       </void> 
       <void property="attributes"> 
        <void method="put"> 
         <string>bounds</string> 
         <object class="org.jgraph.graph.AttributeMap$SerializableRectangle2D"> 
          <void property="height"> 
           <double>50.0</double> 
          </void> 
          <void property="width"> 
           <double>191.0</double> 
          </void> 
          <void property="x"> 
           <double>9.0</double> 
          </void> 
          <void property="y"> 
           <double>116.0</double> 
          </void> 
         </object> 
        </void> 
       </void> 
      </object> 
     </void> 
     <void method="add"> 
      <object id="ClassModelClassCell1" class="cz.cvut.indepmod.classmodel.workspace.cell.ClassModelClassCell"> 
       <object idref="ClassModel0"/> 
       <void method="add"> 
        <object idref="DefaultPort2"/> 
       </void> 
       <void property="attributes"> 
        <void method="put"> 
         <string>bounds</string> 
         <object class="org.jgraph.graph.AttributeMap$SerializableRectangle2D"> 
          <void property="height"> 
           <double>36.0</double> 
          </void> 
          <void property="width"> 
           <double>78.0</double> 
          </void> 
          <void property="x"> 
           <double>266.0</double> 
          </void> 
          <void property="y"> 
           <double>123.0</double> 
          </void> 
         </object> 
        </void> 
       </void> 
      </object> 
     </void> 
     <void method="add"> 
      <object id="ClassModelClassCell2" class="cz.cvut.indepmod.classmodel.workspace.cell.ClassModelClassCell"> 
       <object class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel"> 
        <string>Class3</string> 
        <void method="addAnotation"> 
         <object class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AnotationModel"> 
          <string>anot3</string> 
          <void method="addAttribute"> 
           <object class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AnotationAttributeModel"> 
            <string>b</string> 
            <void method="addValue"> 
             <string>5</string> 
            </void> 
           </object> 
          </void> 
         </object> 
        </void> 
        <void property="stereotype"> 
         <null/> 
        </void> 
       </object> 
       <void method="add"> 
        <object idref="DefaultPort7"/> 
       </void> 
       <void property="attributes"> 
        <void method="put"> 
         <string>bounds</string> 
         <object class="org.jgraph.graph.AttributeMap$SerializableRectangle2D"> 
          <void property="height"> 
           <double>36.0</double> 
          </void> 
          <void property="width"> 
           <double>90.0</double> 
          </void> 
          <void property="x"> 
           <double>73.0</double> 
          </void> 
          <void property="y"> 
           <double>242.0</double> 
          </void> 
         </object> 
        </void> 
       </void> 
      </object> 
     </void> 
     <void method="add"> 
      <object id="ClassModelClassCell3" class="cz.cvut.indepmod.classmodel.workspace.cell.ClassModelClassCell"> 
       <object class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.InterfaceModel"> 
        <string>Interface1</string> 
        <void method="addMethod"> 
         <object class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.MethodModel"> 
          <object idref="ClassModel0"/> 
          <string>imetoda</string> 
          <object class="java.util.HashSet"> 
           <void method="add"> 
            <object class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AttributeModel"> 
             <object idref="ClassModel0"/> 
             <string>a</string> 
             <object id="Visibility$20" class="cz.cvut.indepmod.classmodel.api.model.Visibility$2" method="valueOf"> 
              <string>PUBLIC</string> 
             </object> 
            </object> 
           </void> 
          </object> 
          <object idref="Visibility$20"/> 
         </object> 
        </void> 
        <void property="stereotype"> 
         <string>interface</string> 
        </void> 
       </object> 
       <void method="add"> 
        <object idref="DefaultPort3"/> 
       </void> 
       <void property="attributes"> 
        <void method="put"> 
         <string>bounds</string> 
         <object class="org.jgraph.graph.AttributeMap$SerializableRectangle2D"> 
          <void property="height"> 
           <double>50.0</double> 
          </void> 
          <void property="width"> 
           <double>140.0</double> 
          </void> 
          <void property="x"> 
           <double>233.0</double> 
          </void> 
          <void property="y"> 
           <double>223.0</double> 
          </void> 
         </object> 
        </void> 
       </void> 
      </object> 
     </void> 
     <void method="add"> 
      <object idref="ClassModelRelation2"/> 
     </void> 
     <void method="add"> 
      <object idref="ClassModelRelation7"/> 
     </void> 
     <void method="add"> 
      <object idref="ClassModelRelation8"/> 
     </void> 
     <void method="add"> 
      <object id="ClassModelClassCell4" class="cz.cvut.indepmod.classmodel.workspace.cell.ClassModelClassCell"> 
       <object class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel"> 
        <string>Class4</string> 
        <void property="stereotype"> 
         <null/> 
        </void> 
       </object> 
       <void method="add"> 
        <object idref="DefaultPort5"/> 
       </void> 
       <void property="attributes"> 
        <void method="put"> 
         <string>bounds</string> 
         <object class="org.jgraph.graph.AttributeMap$SerializableRectangle2D"> 
          <void property="height"> 
           <double>22.0</double> 
          </void> 
          <void property="width"> 
           <double>38.0</double> 
          </void> 
          <void property="x"> 
           <double>423.0</double> 
          </void> 
          <void property="y"> 
           <double>117.0</double> 
          </void> 
         </object> 
        </void> 
       </void> 
      </object> 
     </void> 
     <void method="add"> 
      <object idref="ClassModelRelation5"/> 
     </void> 
     <void method="add"> 
      <object id="ClassModelClassCell5" class="cz.cvut.indepmod.classmodel.workspace.cell.ClassModelClassCell"> 
       <object class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel"> 
        <string>Class5</string> 
        <void property="stereotype"> 
         <null/> 
        </void> 
       </object> 
       <void method="add"> 
        <object idref="DefaultPort6"/> 
       </void> 
       <void property="attributes"> 
        <void method="put"> 
         <string>bounds</string> 
         <object class="org.jgraph.graph.AttributeMap$SerializableRectangle2D"> 
          <void property="height"> 
           <double>22.0</double> 
          </void> 
          <void property="width"> 
           <double>38.0</double> 
          </void> 
          <void property="x"> 
           <double>425.0</double> 
          </void> 
          <void property="y"> 
           <double>230.0</double> 
          </void> 
         </object> 
        </void> 
       </void> 
      </object> 
     </void> 
     <void method="add"> 
      <object idref="ClassModelRelation6"/> 
     </void> 
     <void method="add"> 
      <object id="ClassModelClassCell6" class="cz.cvut.indepmod.classmodel.workspace.cell.ClassModelClassCell"> 
       <object class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel"> 
        <string>Class6</string> 
        <void method="addMethod"> 
         <object class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.MethodModel"> 
          <object idref="ClassModel0"/> 
          <string>metoda6</string> 
          <object class="java.util.HashSet"/> 
          <object idref="Visibility$40"/> 
         </object> 
        </void> 
        <void property="stereotype"> 
         <null/> 
        </void> 
       </object> 
       <void method="add"> 
        <object idref="DefaultPort4"/> 
       </void> 
       <void property="attributes"> 
        <void method="put"> 
         <string>bounds</string> 
         <object class="org.jgraph.graph.AttributeMap$SerializableRectangle2D"> 
          <void property="height"> 
           <double>36.0</double> 
          </void> 
          <void property="width"> 
           <double>105.0</double> 
          </void> 
          <void property="x"> 
           <double>311.0</double> 
          </void> 
          <void property="y"> 
           <double>22.0</double> 
          </void> 
         </object> 
        </void> 
       </void> 
      </object> 
     </void> 
     <void method="add"> 
      <object idref="ClassModelRelation3"/> 
     </void> 
     <void method="add"> 
      <object idref="ClassModelRelation4"/> 
     </void> 
     <void method="add"> 
      <object id="ClassModelClassCell7" class="cz.cvut.indepmod.classmodel.workspace.cell.ClassModelClassCell"> 
       <object class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.EnumerationModel"> 
        <string>Enum1</string> 
        <void method="addAttribute"> 
         <object class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AttributeModel"> 
          <object idref="ClassModel0"/> 
          <string>atr1</string> 
          <object idref="Visibility$20"/> 
         </object> 
        </void> 
        <void property="stereotype"> 
         <string>enumeration</string> 
        </void> 
       </object> 
       <void method="add"> 
        <object idref="DefaultPort1"/> 
       </void> 
       <void property="attributes"> 
        <void method="put"> 
         <string>bounds</string> 
         <object class="org.jgraph.graph.AttributeMap$SerializableRectangle2D"> 
          <void property="height"> 
           <double>50.0</double> 
          </void> 
          <void property="width"> 
           <double>106.0</double> 
          </void> 
          <void property="x"> 
           <double>64.0</double> 
          </void> 
          <void property="y"> 
           <double>16.0</double> 
          </void> 
         </object> 
        </void> 
       </void> 
      </object> 
     </void> 
     <void method="add"> 
      <object idref="ClassModelRelation0"/> 
     </void> 
     <void method="add"> 
      <object idref="ClassModelRelation1"/> 
     </void> 
    </object> 
    <object class="org.jgraph.graph.AttributeMap"/> 
   </object> 
   <object class="cz.cvut.indepmod.classmodel.workspace.cell.ClassModelCellViewFactory"/> 
   <array class="org.jgraph.graph.CellView" length="25"> 
    <void index="0"> 
     <object class="org.jgraph.graph.PortView"> 
      <void property="cell"> 
       <object idref="DefaultPort0"/> 
      </void> 
     </object> 
    </void> 
    <void index="1"> 
     <object class="org.jgraph.graph.PortView"> 
      <void property="cell"> 
       <object idref="DefaultPort7"/> 
      </void> 
     </object> 
    </void> 
    <void index="2"> 
     <object class="org.jgraph.graph.EdgeView"> 
      <void property="cell"> 
       <object idref="ClassModelRelation3"/> 
      </void> 
     </object> 
    </void> 
    <void index="3"> 
     <object class="cz.cvut.indepmod.classmodel.workspace.cell.ClassModelVertexView"> 
      <object idref="ClassModelClassCell1"/> 
     </object> 
    </void> 
    <void index="4"> 
     <object class="cz.cvut.indepmod.classmodel.workspace.cell.ClassModelVertexView"> 
      <object idref="ClassModelClassCell4"/> 
     </object> 
    </void> 
    <void index="5"> 
     <object class="org.jgraph.graph.EdgeView"> 
      <void property="cell"> 
       <object idref="ClassModelRelation1"/> 
      </void> 
     </object> 
    </void> 
    <void index="6"> 
     <object class="org.jgraph.graph.EdgeView"> 
      <void property="cell"> 
       <object idref="ClassModelRelation5"/> 
      </void> 
     </object> 
    </void> 
    <void index="7"> 
     <object class="cz.cvut.indepmod.classmodel.workspace.cell.ClassModelVertexView"> 
      <object idref="ClassModelClassCell0"/> 
     </object> 
    </void> 
    <void index="8"> 
     <object class="cz.cvut.indepmod.classmodel.workspace.cell.ClassModelVertexView"> 
      <object idref="ClassModelClassCell3"/> 
     </object> 
    </void> 
    <void index="9"> 
     <object class="org.jgraph.graph.PortView"> 
      <void property="cell"> 
       <object idref="DefaultPort6"/> 
      </void> 
     </object> 
    </void> 
    <void index="10"> 
     <object class="org.jgraph.graph.PortView"> 
      <void property="cell"> 
       <object idref="DefaultPort2"/> 
      </void> 
     </object> 
    </void> 
    <void index="11"> 
     <object class="cz.cvut.indepmod.classmodel.workspace.cell.ClassModelVertexView"> 
      <object idref="ClassModelClassCell5"/> 
     </object> 
    </void> 
    <void index="12"> 
     <object class="cz.cvut.indepmod.classmodel.workspace.cell.ClassModelVertexView"> 
      <object idref="ClassModelClassCell2"/> 
     </object> 
    </void> 
    <void index="13"> 
     <object class="org.jgraph.graph.EdgeView"> 
      <void property="cell"> 
       <object idref="ClassModelRelation7"/> 
      </void> 
     </object> 
    </void> 
    <void index="14"> 
     <object class="org.jgraph.graph.EdgeView"> 
      <void property="cell"> 
       <object idref="ClassModelRelation0"/> 
      </void> 
     </object> 
    </void> 
    <void index="15"> 
     <object class="org.jgraph.graph.PortView"> 
      <void property="cell"> 
       <object idref="DefaultPort3"/> 
      </void> 
     </object> 
    </void> 
    <void index="16"> 
     <object class="org.jgraph.graph.PortView"> 
      <void property="cell"> 
       <object idref="DefaultPort1"/> 
      </void> 
     </object> 
    </void> 
    <void index="17"> 
     <object class="org.jgraph.graph.EdgeView"> 
      <void property="cell"> 
       <object idref="ClassModelRelation6"/> 
      </void> 
     </object> 
    </void> 
    <void index="18"> 
     <object class="org.jgraph.graph.EdgeView"> 
      <void property="cell"> 
       <object idref="ClassModelRelation2"/> 
      </void> 
     </object> 
    </void> 
    <void index="19"> 
     <object class="org.jgraph.graph.PortView"> 
      <void property="cell"> 
       <object idref="DefaultPort5"/> 
      </void> 
     </object> 
    </void> 
    <void index="20"> 
     <object class="cz.cvut.indepmod.classmodel.workspace.cell.ClassModelVertexView"> 
      <object idref="ClassModelClassCell6"/> 
     </object> 
    </void> 
    <void index="21"> 
     <object class="org.jgraph.graph.PortView"> 
      <void property="cell"> 
       <object idref="DefaultPort4"/> 
      </void> 
     </object> 
    </void> 
    <void index="22"> 
     <object class="org.jgraph.graph.EdgeView"> 
      <void property="cell"> 
       <object idref="ClassModelRelation8"/> 
      </void> 
     </object> 
    </void> 
    <void index="23"> 
     <object class="org.jgraph.graph.EdgeView"> 
      <void property="cell"> 
       <object idref="ClassModelRelation4"/> 
      </void> 
     </object> 
    </void> 
    <void index="24"> 
     <object class="cz.cvut.indepmod.classmodel.workspace.cell.ClassModelVertexView"> 
      <object idref="ClassModelClassCell7"/> 
     </object> 
    </void> 
   </array> 
   <array class="org.jgraph.graph.CellView" length="0"/> 
   <boolean>false</boolean> 
  </object> 
  <object class="cz.cvut.indepmod.classmodel.api.model.DiagramType" method="valueOf"> 
   <string>CLASS</string> 
  </object> 
  <string>Java</string> 
  <void method="addDynamicDataType"> 
   <object class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.TypeModel"> 
    <string>int</string> 
   </object> 
  </void> 
  <void method="addDynamicDataType"> 
   <object class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.TypeModel"> 
    <string>String</string> 
   </object> 
  </void> 
  <void method="addDynamicDataType"> 
   <object class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.TypeModel"> 
    <string>double</string> 
   </object> 
  </void> 
  <void method="addDynamicDataType"> 
   <object class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.TypeModel"> 
    <string>boolean</string> 
   </object> 
  </void> 
  <void method="addDynamicDataType"> 
   <object class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.TypeModel"> 
    <string>void</string> 
   </object> 
  </void> 
  <void method="addDynamicDataType"> 
   <object class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.TypeModel"> 
    <string>long</string> 
   </object> 
  </void> 
  <void method="addDynamicDataType"> 
   <object class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.TypeModel"> 
    <string></string> 
   </object> 
  </void> 
  <void method="addDynamicDataType"> 
   <object class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.TypeModel"> 
    <string>Object</string> 
   </object> 
  </void> 
  <void method="addDynamicDataType"> 
   <object class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.TypeModel"> 
    <string>char</string> 
   </object> 
  </void> 
  <void method="addDynamicDataType"> 
   <object class="cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.TypeModel"> 
    <string>float</string> 
   </object> 
  </void> 
  <void method="addStereotype"> 
   <string></string> 
  </void> 
  <void method="addStereotype"> 
   <string>enumeration</string> 
  </void> 
  <void method="addStereotype"> 
   <string>interface</string> 
  </void> 
  <void property="classCounter"> 
   <int>6</int> 
  </void> 
  <void property="enumerationCounter"> 
   <int>1</int> 
  </void> 
  <void property="interfaceCounter"> 
   <int>1</int> 
  </void> 
 </object> 
</java> 
