////////////////////////////////////////////////////////////////
//
// Copyright (C) 2007 Affymetrix, Inc.
//
// This library is free software; you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License 
// (version 2.1) as published by the Free Software Foundation.
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
////////////////////////////////////////////////////////////////

#include "stdafx.h"
#include "ProbeSetMultiDataCopyNumberDataCOM.h"
#include "COMStringUtils.h"

using namespace affymetrix_calvin_parameter;
using namespace affymetrix_calvin_data;


// CProbeSetMultiDataCopyNumberDataCOM

STDMETHODIMP CProbeSetMultiDataCopyNumberDataCOM::InterfaceSupportsErrorInfo(REFIID riid)
{
	static const IID* arr[] = 
	{
		&IID_IProbeSetMultiDataCopyNumberData
	};

	for (int i=0; i < sizeof(arr) / sizeof(arr[0]); i++)
	{
		if (InlineIsEqualGUID(*arr[i],riid))
			return S_OK;
	}
	return S_FALSE;
}

STDMETHODIMP CProbeSetMultiDataCopyNumberDataCOM::get_name(BSTR* pVal)
{
	*pVal = COMStringUtils::ConvertString(data.name);
	return S_OK;
}

STDMETHODIMP CProbeSetMultiDataCopyNumberDataCOM::get_chr(int* pVal)
{
	*pVal = data.chr;
	return S_OK;
}

STDMETHODIMP CProbeSetMultiDataCopyNumberDataCOM::get_position(int* pVal)
{
	*pVal = data.position;
    return S_OK;
}

STDMETHODIMP CProbeSetMultiDataCopyNumberDataCOM::get_metricCount(int *pVal)
{
    *pVal = (int)data.metrics.size();
    return S_OK;
}

STDMETHODIMP CProbeSetMultiDataCopyNumberDataCOM::GetMetricName(int index, BSTR* pVal)
{
    ParameterNameValueType &nv = data.metrics[index];
    *pVal = COMStringUtils::ConvertWString(nv.GetName());
    return S_OK;
}

STDMETHODIMP CProbeSetMultiDataCopyNumberDataCOM::GetMetric(int index, VARIANT* pVal)
{
    ParameterNameValueType &nv = data.metrics[index];
	switch (nv.GetParameterType())
	{
		case ParameterNameValueType::Int8Type:
			pVal->vt = VT_I4;
            pVal->lVal = (int)nv.GetValueInt8();
			break;

		case ParameterNameValueType::UInt8Type:
            pVal->vt = VT_I4;
			pVal->lVal = (int)nv.GetValueUInt8();
			break;

		case ParameterNameValueType::Int16Type:
            pVal->vt = VT_I4;
			pVal->lVal = (int)nv.GetValueInt16();
			break;

		case ParameterNameValueType::UInt16Type:
            pVal->vt = VT_I4;
			pVal->lVal = (int)nv.GetValueUInt16();
			break;

		case ParameterNameValueType::Int32Type:
            pVal->vt = VT_I4;
			pVal->lVal = (int)nv.GetValueInt32();
			break;

		case ParameterNameValueType::UInt32Type:
            pVal->vt = VT_I4;
			pVal->lVal = nv.GetValueUInt32();
			break;

		case ParameterNameValueType::FloatType:
            pVal->vt = VT_R4;
			pVal->fltVal = nv.GetValueFloat();
			break;

		case ParameterNameValueType::AsciiType:
            pVal->vt = VT_BSTR;
			pVal->bstrVal = COMStringUtils::ConvertString(nv.GetValueAscii());
			break;

		case ParameterNameValueType::TextType:
            pVal->vt = VT_BSTR;
			pVal->bstrVal = COMStringUtils::ConvertWString(nv.GetValueText());
			break;
	}
    return S_OK;
}
