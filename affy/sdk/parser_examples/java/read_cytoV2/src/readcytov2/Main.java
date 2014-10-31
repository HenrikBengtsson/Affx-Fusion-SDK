/////////////////////////////////////////////////////////////////
//
// Copyright (C) 2009 Affymetrix, Inc.
//
// This library is free software; you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published
// by the Free Software Foundation; either version 2.1 of the License,
// or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful, but
// WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
// or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
// for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with this library; if not, write to the Free Software Foundation, Inc.,
// 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA 
//
/////////////////////////////////////////////////////////////////

package readcytov2;

import affymetrix.calvin.data.AllelePeaks;
import affymetrix.calvin.data.CHPMultiDataData.MultiDataType;
import affymetrix.calvin.data.ChromosomeMultiDataSummaryData;
import affymetrix.calvin.data.ChromosomeSegmentData;
import affymetrix.calvin.data.CytoGenotypingCall;
import affymetrix.calvin.data.MarkerABSignals;
import affymetrix.calvin.data.ProbeSetMultiDataCopyNumberData;
import affymetrix.calvin.exception.UnsignedOutOfLimitsException;
import affymetrix.calvin.parameter.ParameterNameValue;
import affymetrix.fusion.chp.FusionCHPData;
import affymetrix.fusion.chp.FusionCHPDataReg;
import affymetrix.fusion.chp.FusionCHPMultiDataData;
import java.util.List;

/**
 * Read and output to the console the contents of a CYCHP file.
 */
public class Main {

    public static void printParams(List<ParameterNameValue> params) {
        for (int i = 0; i < params.size(); i++) {
            try
            {
                ParameterNameValue p = params.get(i);
                System.out.print(p.getName() + " = ");
                switch (p.getParameterType()) {
                    case Int8Type:
                        System.out.println(p.getValueInt8());
                        break;

                    case Int16Type:
                        System.out.println(p.getValueInt16());
                        break;

                    case Int32Type:
                        System.out.println(p.getValueInt32());
                        break;

                    case UInt8Type:
                        System.out.println(p.getValueUInt8().toShort());
                        break;

                    case UInt16Type:
                        System.out.println(p.getValueUInt16().toInt());
                        break;

                    case UInt32Type:
                        System.out.println(p.getValueUInt32().toLong());
                        break;

                    case FloatType:
                        System.out.println(p.getValueFloat());
                        break;

                    case TextType:
                        System.out.println(p.getValueText());
                        break;

                    case AsciiType:
                        System.out.println(p.getValueAscii());
                        break;
                    default:
                        break;
                }
            }
            catch (UnsignedOutOfLimitsException ex)
            {
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        FusionCHPMultiDataData.registerReader();
        try {
            String fileName = null;
            if (args.length == 0) {
                fileName = "c:\\temp\\test.cychp";
            } else {
                fileName = args[0];
            }
            FusionCHPData chp = FusionCHPDataReg.read(fileName);
            if (chp == null) {
                System.out.println("Failed to read the file: " + fileName);
                return;
            }
            FusionCHPMultiDataData mchp = FusionCHPMultiDataData.fromBase(chp);
            if (mchp == null) {
                System.out.println("The example is for CYCHP files only.");
                return;
            }

            System.out.println("File name = " + fileName);
            System.out.println("Array type = " + mchp.getArrayType());
            System.out.println("Alg version = " + mchp.getAlgVersion());
            System.out.println("Alg name = " + mchp.getAlgName());
            System.out.println("File Id = " + mchp.getFileId());
            printParams(mchp.getAlgParams());
            printParams(mchp.getSummaryParams());

            //mchp.getEntryCount(MultiDataType.ChromosomeSummaryMultiDataType);

            MultiDataType dataTypes1[] = {
                MultiDataType.ChromosomeSummaryMultiDataType
            };

            MultiDataType dataTypes2[] = {
                MultiDataType.SegmentCNMultiDataType,
                MultiDataType.SegmentLOHMultiDataType,
                MultiDataType.SegmentCNNeutralLOHMultiDataType,
                MultiDataType.SegmentNormalDiploidMultiDataType,
                MultiDataType.SegmentMosaicismMultiDataType,
                MultiDataType.SegmentNoCallMultiDataType
            };
            
             MultiDataType dataTypes3[] = {
                MultiDataType.CopyNumberMultiDataType
            };

            MultiDataType dataTypes4[] = {
                MultiDataType.MarkerABSignalsMultiDataType
            };
            
            MultiDataType dataTypes5[] = {
                MultiDataType.AllelePeaksMultiDataType
            };

            for (int i = 0; i < dataTypes1.length; i++) {
                int n = mchp.getEntryCount(dataTypes1[i]);
                System.out.println("#Entries(" + dataTypes1[i].toString() + ")=" + n);
                ChromosomeMultiDataSummaryData d;
                for (int j = 0; j < Math.min(n, 3); j++) {
                    d = mchp.getChromosomeSummaryEntry(dataTypes1[i], j);
                    System.out.println("Chr = " + d.getChr().toShort());
                    System.out.println("StartIndex = " + d.getStartIndex().toLong());
                    System.out.println("MarkerCount = " + d.getMarkerCount().toLong());
                    System.out.println("MinSignal = " + d.getMinSignal());
                    System.out.println("MaxSignal = " + d.getMaxSignal());
                    System.out.println("MedianCnState = " + d.getMedianCnState());
                    System.out.println("HetFrequency = " + d.getHetFrequency());
                    System.out.println("HomFrequency = " + d.getHomFrequency());
                    printParams(d.getMetrics());
                }
            }

            for (int i = 0; i < dataTypes2.length; i++) {
                int n = mchp.getEntryCount(dataTypes2[i]);
                System.out.println("#Entries(" + dataTypes2[i].toString() + ")=" + n);
                ChromosomeSegmentData d;
                for (int j = 0; j < Math.min(n, 3); j++) {
                    d = mchp.getChromosomeSegmentEntry(dataTypes2[i], j);
                    System.out.println("Chr = " + d.getChr().toShort());
                    System.out.println("SegmentId = " + d.getSegmentId().toLong());
                    System.out.println("MarkerCount = " + d.getMarkerCount());
                    System.out.println("StartPosition = " + d.getStartPosition().toLong());
                    System.out.println("StopPosition = " + d.getStopPosition().toLong());
                    System.out.println("MeanMarkerDistance = " + d.getMeanMarkerDistance().toLong());
                    printParams(d.getMetrics());
                }
            }

            for (int i = 0; i < dataTypes3.length; i++) {
                int n = mchp.getEntryCount(dataTypes3[i]);
                System.out.println("#Entries(" + dataTypes3[i].toString() + ")=" + n);
                ProbeSetMultiDataCopyNumberData d;
                for (int j = 0; j < Math.min(n, 3); j++) {
                    d = mchp.getCopyNumberEntry(dataTypes3[i], j);
                    System.out.println("Name = " + d.getName());
                    System.out.println("Chr = " + d.getChr());
                    System.out.println("Position = " + d.getPosition());
                    printParams(d.getMetrics());
                }
            }

            for (int i = 0; i < dataTypes4.length; i++) {
                int n = mchp.getEntryCount(dataTypes4[i]);
                System.out.println("#Entries(" + dataTypes4[i].toString() + ")=" + n);
                MarkerABSignals d;
                for (int j = 0; j < Math.min(n, 3); j++) {
                    d = mchp.getMarkerABSignalsEntry(dataTypes4[i], j);
                    System.out.println("Index = " + d.getIndex().toLong());
                    printParams(d.getMetrics());
                }
            }

            for (int i = 0; i < dataTypes5.length; i++) {
                int n = mchp.getEntryCount(dataTypes5[i]);
                System.out.println("#Entries(" + dataTypes5[i].toString() + ")=" + n);
                AllelePeaks d;
                for (int j = 0; j < Math.min(n, 3); j++) {
                    d = mchp.getAllelePeaksEntry(dataTypes5[i], j);
                    System.out.println("Name = " + d.getName());
                    System.out.println("Chr = " + d.getChr());
                    System.out.println("Position = " + d.getPosition().toLong());
                    printParams(d.getMetrics());
                }
            }
            
            int n = mchp.getEntryCount(MultiDataType.CytoGenotypingCallMultiDataType);
            System.out.println("#Entries(" + MultiDataType.CytoGenotypingCallMultiDataType.toString() + ")=" + n);
            CytoGenotypingCall call;
            for (int j = 0; j < Math.min(n, 3); j++) {
                call = mchp.getCytoGenotypingCall(MultiDataType.CytoGenotypingCallMultiDataType, j);
                System.out.println("ProbeIndex = " + call.getIndex());
                System.out.println("Call = " + call.getCall());
                System.out.println("ASignal = " + call.getASignal());
                System.out.println("BSignal = " + call.getBSignal());
                System.out.println("Confidence = " + call.getConfidence());
                System.out.println("Contrast = " + call.getContrast());
                System.out.println("ForcedCall = " + call.getForcedCall());              
                System.out.println("SignalStrength = " + call.getSignalStrength());
                printParams(call.getMetrics());
            }
            mchp.clear();

        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
    }
}
