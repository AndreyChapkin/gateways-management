import { useCallback, useRef, useState } from "react";
import { createDevice } from "../../../api/device-api";
import { GatewayDto } from "../../../type/gateway-types";
import { DeviceDto, DeviceStatus } from "../../../type/device-types";
import ModalWindow from "../../../component/modal-window/ModalWindow";

export interface CreateDeviceFormProps {
  gateway: GatewayDto;
  formBackgroundClick?: (e?: MouseEvent) => void;
}

const CreateDeviceForm: React.FC<CreateDeviceFormProps> = ({ gateway, formBackgroundClick }) => {
  // state
  // elements
  const vendorFieldRef = useRef<HTMLInputElement>();
  const uidFieldRef = useRef<HTMLInputElement>();
  const statusFieldRef = useRef<HTMLSelectElement>();
  // handlers
  const createDeviceHandler = useCallback(async () => {
    const deviceDto: DeviceDto = {
      id: null,
      vendor: vendorFieldRef.current.value,
      uid: parseInt(uidFieldRef.current.value),
      status: statusFieldRef.current.value as DeviceStatus,
      createDate: null,
    };
    await createDevice(gateway.id, deviceDto);
    location.reload();
  }, [gateway.id]);
  const backgroundClickHandler = useCallback((e: MouseEvent) => {
    formBackgroundClick && formBackgroundClick(e);
  }, [formBackgroundClick]);

  return (
    <ModalWindow onBackgroundClick={backgroundClickHandler}>
      <div className="col">
        <label htmlFor="vendor">Vendor: </label>
        <input ref={vendorFieldRef} type="text" id="vendor" />
        <label htmlFor="uid">UID: </label>
        <input ref={uidFieldRef} type="text" id="uid" />
        <label htmlFor="status">Status: </label>
        <select ref={statusFieldRef} name="status" id="status" defaultValue="OFFLINE">
          <option value="OFFLINE">OFFLINE</option>
          <option value="ONLINE">ONLINE</option>
        </select>
        <button className="v-normal-spaced" onClick={createDeviceHandler}>Create</button>
      </div>
    </ModalWindow>
  );
};

export default CreateDeviceForm;
