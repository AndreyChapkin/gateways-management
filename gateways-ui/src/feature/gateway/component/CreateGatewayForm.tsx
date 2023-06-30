import { useCallback, useRef } from "react";
import { createGateway } from "../../../api/gateway-api";
import { GatewayDto } from "../../../type/gateway-types";
import ModalWindow from "../../../component/modal-window/ModalWindow";
import { ErrorDto } from "../../../type/general-types";

export interface CreateGatewayFormProps {
  formBackgroundClick?: (e?: MouseEvent) => void;
  responseCallback?: (body: ErrorDto) => void;
}

const CreateGatewayForm: React.FC<CreateGatewayFormProps> = ({
  formBackgroundClick,
  responseCallback,
}) => {
  // state
  // elements
  const nameFieldRef = useRef<HTMLInputElement>();
  const ipAddressFieldRef = useRef<HTMLInputElement>();
  const serialNumberFieldRef = useRef<HTMLInputElement>();
  // handlers
  const createGatewayHandler = useCallback(async () => {
    const gatewayDto: GatewayDto = {
      id: null,
      name: nameFieldRef.current.value,
      ipv4Address: ipAddressFieldRef.current.value,
      serialNumber: serialNumberFieldRef.current.value,
      devices: null,
    };
    let responseData = await createGateway(gatewayDto);
    if (responseData) {
      responseCallback && responseCallback(responseData);
    } else {
      location.reload();
    }
  }, []);

  const backgroundClickHandler = useCallback((e: MouseEvent) => {
    formBackgroundClick && formBackgroundClick(e);
  }, [formBackgroundClick]);

  return (
    <ModalWindow onBackgroundClick={backgroundClickHandler}>
      <div className="col">
        <label htmlFor="name">Name: </label>
        <input ref={nameFieldRef} type="text" id="name" />
        <label htmlFor="ipAddress">IP address: </label>
        <input ref={ipAddressFieldRef} type="text" id="ipAddress" />
        <label htmlFor="serialNumber">Serial number: </label>
        <input ref={serialNumberFieldRef} type="text" id="serialNumber" />
        <button className="normal-spaced" onClick={createGatewayHandler}>Create</button>
      </div>
    </ModalWindow>
  );
};

export default CreateGatewayForm;
